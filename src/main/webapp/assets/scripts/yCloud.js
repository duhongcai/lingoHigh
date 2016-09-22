var yCloud = yCloud || {};

yCloud.layoutFit = function(){
	var head = $('.headerbar');
	var box = $('.mainlayout');
	var sideBar = box.find('.sidebar');
	var content = box.find('.icontent');
	var iframe = content.find('.frame');
	if(head.length==0){return;}
	fitLayout();
	$(window).resize(function(){
		fitLayout();
	});
	function fitLayout(){
		var h = $(window).height();
		box.height(h - head.outerHeight());
		content.height(h - head.outerHeight());
		iframe.height(content.height()-$('.tagbox').outerHeight());
	}	
};
yCloud.sideBar = function(){
	var box = $('.mainlayout');
	var sideBar = box.find('.sidebar');
	var frameBox = box.find('.frame');
	var iframe = $('#mainiFrame');
	var tabTag = $('#tabTag');
	var tabBox = $('#tabBox');
	var flip = $('#tabFlip');
	var liw = 100;
	//homepage initiation
	var _id = "Jtab"+tabTag.find('li.on').attr('code');//update by zx 2016-05-04
	tabTag.find('li.on').attr('jid',_id);
	frameBox.find('iframe.home').attr('frameid',_id);
	//
	sideBar.on('click','dt',function(){
		$(this).closest('dl').siblings().find('dd').slideUp(200);
		if($(this).siblings('dd').is(":visible")){
			$(this).siblings('dd').slideUp(200);
		}else{
			$(this).siblings('dd').slideDown(200);
		}
	});

	//sidebar clicked
	sideBar.on('click','dd a',function(){
		var id = $(this).attr('tid');
		if(!id){
			id="Jtab"+$(this).attr('code');//update by zx 2016-05-04
			$(this).attr('tid',id);
		}
		if($(this).hasClass('on')){return;}
		$(this).addClass('on')
			.siblings('a').removeClass('on')
			.closest('dl').addClass('on')
			.siblings('dl').removeClass('on')
			.find('dd a').removeClass('on');
		tabTag.find('li').removeClass('on');

		var mirror = tabTag.find('li[jid="'+id+'"]');
		if(mirror.length==0){//update by zx 2016-05-04
			tabTag.append('<li jid="'+id+'" subSystemCode="'+$(this).attr('subSystemCode')+'" title="'+$(this).text()+'" class="on"><a href="javascript:;">'+$(this).text()+'<i>×</i></a></li>');
			tabTag.css({'width':tabTag.find('li').length*liw-(tabTag.find('li').length-1)});
			tabBox.scrollLeft(1000000);
		}else{
			mirror.addClass('on');
			if(tabBox.scrollLeft()+tabBox.width()<(mirror.index()+1)*liw){
				//被点击tab超出当前区域尾部
				var l = (mirror.index()+1)*liw-tabBox.width()-mirror.index();
				/*tabBox.scrollLeft((mirror.index()+1)*liw-tabBox.width()-mirror.index());*/
				tabBox.stop(false).animate({scrollLeft:l},300);
			}else if(tabBox.scrollLeft()>mirror.index()*liw){
				var l = mirror.index()*liw-mirror.index();
				//tabBox.scrollLeft(mirror.index()*liw-mirror.index());
				tabBox.stop(false).animate({scrollLeft:l},300);
			}
		}
		appendTab($(this).attr('data-src'),$(this).attr('tid'));
		/*if($(this).attr('data-src')){
			//console.log($(this).attr('tid'));
			//iframe.attr('src',$(this).attr('data-src'));
			appendTab($(this).attr('data-src'),$(this).attr('tid'));
		}*/
	});

	//tab clicked
	tabTag.on('click','a i',function(e){
		var li = $(this).closest('li');
		var id ;
		var mirror;
		var temp = li.prev();
		if(li.hasClass('on')){
			li.prev().addClass('on');
			id = li.prev().attr('jid');
			mirror = sideBar.find('a[tid="'+id+'"]');
			setSideOn(mirror);
		}
		closeTab(li.attr('jid'),id);
		li.remove();
		tabTag.css({'width':tabTag.find('li').length*liw-(tabTag.find('li').length-1)});
		if(tabBox.scrollLeft()>=temp.index()*liw){
			var l = tabTag.find('.on').index()*liw-temp.index();
			//tabBox.scrollLeft(tabTag.find('.on').index()*liw-temp.index());
			tabBox.stop(false).animate({scrollLeft:l},300);
		}
		if(tabTag.find('li').length==1){
			removeSideOn();
		}
		e.stopPropagation();
	}).on('click','a',function(){
		
		var li = $(this).closest('li');
		
		 //当前页面对应的子系统不是当前选择on的则，选中之 by zx 2016-05-04 start----
		 if($("#pMenuUl li .on").parent().attr("data-id")!=li.attr('subSystemCode'))
		 {
			 $("#pMenuUl li").each(function ()
			 {
				 if($(this).attr("data-id")==li.attr('subSystemCode'))
				 {
					 $("#pMenuUl li a").removeClass("on");
					 $(this).find("a").addClass("on");
					 showLeftMenus($(this).attr("data-id"));
				 }
			 });
		 }
		//by zx 2016-05-04 end----
		var id = li.attr('jid');
		var mirror = sideBar.find('a[tid="'+id+'"]');
		if(!li.hasClass('on')){
			mirror.trigger('click');
		}
		if(li.hasClass('home')){
			closeTab('',id);
			removeSideOn();
		}else{
			setSideOn(mirror);
		}
		if(tabBox.scrollLeft()+tabBox.width()<(li.index()+1)*liw){
			//被点击tab超出当前区域尾部
			var l = (li.index()+1)*liw-tabBox.width()-li.index();
			//tabBox.scrollLeft((li.index()+1)*liw-tabBox.width()-li.index());
			tabBox.stop(false).animate({scrollLeft:l},300);
		}else if(tabBox.scrollLeft()>li.index()*liw){
			var l = li.index()*liw;
			//tabBox.scrollLeft(li.index()*liw);
			tabBox.stop(false).animate({scrollLeft:l},300)
		}
		li.addClass('on').siblings().removeClass('on');
	});

	flip.find('.prev').click(function(){
		flipTab(-99);
	});
	flip.find('.next').click(function(){
		flipTab(99);
	});

	//
	function appendTab(src,id){
		if(frameBox.find('iframe[frameid="'+id+'"]').length>0){
			frameBox.find('iframe[frameid="'+id+'"]').show().siblings().hide();
		}else{
			if(src){
				var html = '<iframe src="'+ src +'" frameid="'+ id +'" frameborder="0"></iframe>';
				frameBox.find('iframe').hide();
				frameBox.append(html);
			}
		}
	}
	function closeTab(cid,sid){
		//cid remove tab
		//sid next show tab
		console.log(cid,sid);
		if(cid && cid!=''){
			frameBox.find('iframe[frameid="'+cid+'"]').remove();
		}
		frameBox.find('iframe[frameid="'+sid+'"]').show().siblings().hide();
	}
	//flip the tab and reset tabbox' scrollLeft
	function flipTab(dis){
		var l = tabBox.scrollLeft();
		tabBox.stop(false).animate({scrollLeft:l+dis},300);
	}
	//remove sidebar's current style
	function removeSideOn(){
		sideBar.find('dl.on dt').trigger('click');
		sideBar.stop(false).find('.on').removeClass('on');
	}
	//click tab and set sidebar
	function setSideOn(obj){
		if(!obj.closest('dd').is(":visible")){
			obj.closest('dl').find('dt').trigger('click');
		}
		obj.addClass('on')
			.siblings('a').removeClass('on')
			.closest('dl').addClass('on')
			.siblings('dl').removeClass('on')
			.find('dd a').removeClass('on');
	}
};
/**
 * 顶部滚动数字方法
 * @param {number}
 */
yCloud.runroll = function(){
	$('.rollnum').each(function(){
		if(!$.support.leadingWhitespace){
			$(this).text($(this).attr('data-num'));
			return;
		}
		if($(this).hasClass('run-over')){return;}
		$(this).addClass('run-over');
		var _this = $(this);
		var num = $(this).attr('data-num');
		if(!num){return;}
		var arr = num.split("");
		var len = arr.length;
		$(this).html('');
		for(var i=0;i<len;i++){
			var _html = '<b style="top:-'+(Math.floor(Math.random()*10)*50)+'px;"><s>0</s><s>1</s><s>2</s><s>3</s><s>4</s><s>5</s><s>6</s><s>7</s><s>8</s><s>9</s></b>';
			if(arr[i]=="."){
				_html = '<b class="dot">.</b>';
			}
			$(this).append(_html);
		}
		if(num.indexOf(".")>-1){
			num = num*100;
			arr = (""+num).split("");
		}
		var b = $(this).find('b:not(".dot")');
		var h = $(this).find('b').height()/10;
		for(var i=0;i<b.length;i++){
			b.eq(i).addClass('num'+i);
		}
		for(var i=0,len=arr.length;i<len;i++){
			var _t = $(this).find(".num"+(len-i-1));
			_t.velocity({"top":-h*arr[len-i-1]+'px'},{
				begin:function(){},
				complete:function(elem){
					if($(elem).hasClass('num0')){
						_this.text(_this.attr('data-num'));
					}
				},
				duration:400,
				delay:i*40,
				easing:'easeInSine'
			});
		}		
	})
};

yCloud.multipSearchOption = function(){
	var container = $('.search-action');
	var box = container.find('.search-input');
	var input = box.find('.floatinput');
	var holder = box.find('.placeholder');
	var keybox = box.find('.keywordbox');
	var showBtn = container.find('.showlist');
	var reslist = container.find('.resultlist');
	box.click(function(){
		input.focus();
	});
	$('.floatinput').on('keydown keyup',function(e){
		var val = $(this).val();
		holder.text(val);
		$(this).css({width:holder.outerWidth()+10});
		if(val==''){
			$(this).removeAttr('style');
		}
	}).on('keydown',function(e){
		var val = $(this).val();
		var last = keybox.children().last();
		if(e.keyCode==8){
			if(val==''){
				var sid = last.attr('selectid');
				if(sid){
					reslist.find('li[selectid="'+sid+'"]').removeAttr('select selectid').find('input').trigger('click');
				}
				last.remove();						
			}
		}else if(e.keyCode == 13){
			var hasSame=false;
			keybox.find('.keyinfo').each(function(){
				if(val == $(this).find('span').text()){
					hasSame = true;
				}
			});
			if(!hasSame){
				if(val.length > 0 && $.trim(val)!=''){
					var sid;
					reslist.find('li').each(function(){
						if(val == $(this).text()){
							sid = 'sel_'+ (new Date().getTime());
							$(this).attr({'select':'selected','selectid':sid});
							$(this).find('input').trigger('click');
						}
					});
					setKeyInfo(val,sid);
					$(this).val('').removeAttr('style');	
				}
			}else{
				$(this).val('').removeAttr('style');
			}
		}
		$(this).focus();
	}).on('blur',function(){
		var val = $(this).val();
		var hasSame=false;
		keybox.find('.keyinfo').each(function(){
			if(val == $(this).find('span').text()){
				hasSame = true;
			}
		});
		if(!hasSame){
			if(val.length > 0 && $.trim(val)!=''){
				var sid;
				reslist.find('li').each(function(){
					if(val == $(this).text()){
						sid = 'sel_'+ (new Date().getTime());
						$(this).attr({'select':'selected','selectid':sid});
						$(this).find('input').trigger('click');
					}
				});
				setKeyInfo(val,sid);
				$(this).val('').removeAttr('style');
			}
		}else{
			$(this).val('').removeAttr('style');
		}
	});
	box.on('click','.keyinfo',function(e){
		reslist.fadeOut(100);
		e.stopPropagation();
	}).on('click',".keyinfo i",function(e){
		var sid = $(this).parent().attr('selectid');
		if(sid){
			reslist.find('li[selectid="'+sid+'"]').removeAttr('select selectid').find('input').trigger('click');
		}
		$(this).parent().remove();
		
	});
	showBtn.click(function(e){
		reslist.fadeIn(100).css('top',container.height()-1);
		$(this).addClass('on');
		e.stopPropagation();
	});
	container.on('click','.resultlist',function(e){
		e.stopPropagation();
	});
	reslist.on('click','.canclelist',function(e){
		reslist.fadeOut(100);
	}).on('click','.confirm-select',function(e){
		reslist.find('li').each(function(){
			if($(this).find('input').is(':checked') && $(this).attr('select')!='selected'){
				var rnd = Math.floor(Math.random()*10000000000);
				keybox.find('.keyinfo[selectid="sel_'+rnd+'"]').remove();
				$(this).attr({'select':'selected','selectid':'sel_'+rnd});
				setKeyInfo($(this).text(),'sel_'+rnd);
			}
		});
		keybox.find('.keyinfo[mark]').remove();
		reslist.fadeOut(100);
		showBtn.removeClass('on');
	}).on('change','.chk-all',function(){
		reslist.find('li input').prop('checked',$(this).prop('checked'));
	}).on('change','li input',function(){
		var len = reslist.find('li input').length;
		var chkedlen = reslist.find('li input:checked').length;
		var li = $(this).closest('li');
		if(len == chkedlen){
			$('.chk-all').prop('checked',true);
		}else{
			$('.chk-all').prop('checked',false);
		}
		if($(this).prop('checked')){
			keybox.removeAttr('mark');
		}else{
			keybox.find('.keyinfo[selectid="'+li.attr('selectid')+'"]').attr('mark','marked');
			li.removeAttr('select selectid');
		}
	}).on('click','label',function(e){
		if($(this).find('input').length == 0){
			var li = $(this).closest('li');
			if(li.attr('select')!=undefined){
				reslist.fadeOut(100);
				showBtn.removeClass('on');
				e.stopPropagation();
				return;
			}
			var rnd = new Date().getTime();
			li.attr({'select':'selected','selectid':'sel_'+rnd});
			setKeyInfo($(this).text(),'sel_'+rnd);
			reslist.fadeOut(100);
			showBtn.removeClass('on');
			e.stopPropagation();
		}
	});
	function setKeyInfo(str,sid){
		var html,sidattr='';
		if(sid != undefined && sid != ""){
			sidattr = ' selectid="'+sid+'"';
		}		
		html = '<div class="keyinfo"'+sidattr+'><span>'+str+'</span><i>×</i></div>';
		keybox.append(html);		
	}
	$(document).click(function(){
		reslist.fadeOut(100);
		showBtn.removeClass('on');
	})
};

yCloud.tableClickHolder = function(){
	$('.powertable').on('click','table.clickhold tr',function(){
		if($(this).parent()[0].nodeName.toUpperCase() == 'THEAD' || $(this).find('th').length>0){return;}
		$(this).toggleClass('selected').siblings().removeClass('selected');
	})
};


$(function(){
	yCloud.layoutFit();
	yCloud.sideBar();
	yCloud.runroll();
	yCloud.tableClickHolder();	
});

function launchFullscreen(element) {
	if(element.requestFullscreen) {
		element.requestFullscreen();
	} else if(element.mozRequestFullScreen) {
		element.mozRequestFullScreen();
	} else if(element.webkitRequestFullscreen) {
		element.webkitRequestFullscreen();
	} else if(element.msRequestFullscreen) {
		element.msRequestFullscreen();
	}
}
function fullScreen(me) {
	if (typeof (document.mozCancelFullScreen) != 'undefined') {
		if (document.mozFullScreen) {
			document.mozCancelFullScreen();			
		} else {
			document.body.mozRequestFullScreen();
		}
	} else if (typeof (document.webkitCancelFullScreen) != 'undefined') {
		if (document.webkitIsFullScreen) {
			document.webkitCancelFullScreen();			
		} else {
			document.body.webkitRequestFullScreen();			
		}
	} else {
		//alert('您的浏览器不支持全屏接口，请按F11键扩展全屏。');
	}
}

