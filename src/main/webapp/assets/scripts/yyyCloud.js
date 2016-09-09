var yyyCloud = yyyCloud || {};

yyyCloud.layoutFit = function(){
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
yyyCloud.sideBar = function(){
	var box = $('.mainlayout');
	var sideBar = box.find('.sidebar');
	var iframe = $('#mainiFrame');
	var tabTag = $('#tabTag');
	var tabBox = $('#tabBox');
	var flip = $('#tabFlip');
	var liw = 100;
	sideBar.find('dt').click(function(){
		$(this).closest('dl').siblings().find('dd').slideUp(200);
		if($(this).siblings('dd').is(":visible")){
			$(this).siblings('dd').slideUp(200);
		}else{
			$(this).siblings('dd').slideDown(200);
		}
	});

	//sidebar clicked
	sideBar.find('dd a').click(function(){
		var id = $(this).attr('tid');		
		if(!id){
			id="Jtab"+(new Date().getTime());
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
		if(mirror.length==0){			
			tabTag.append('<li jid="'+id+'" class="on"><a href="javascript:;">'+$(this).text()+'<i>×</i></a></li>');
			tabTag.css({'width':tabTag.find('li').length*liw-(tabTag.find('li').length-1)});
			tabBox.scrollLeft(1000000);
		}else{
			mirror.addClass('on');
			if(tabBox.scrollLeft()+tabBox.width()<(mirror.index()+1)*liw){
				tabBox.scrollLeft((mirror.index()+1)*liw-tabBox.width());
			}else if(tabBox.scrollLeft()>mirror.index()*liw){
				tabBox.scrollLeft(mirror.index()*liw-mirror.index());
			}
		};
		if($(this).attr('data-src')){
			iframe.attr('src',$(this).attr('data-src'));
		}
	});

	//tab clicked
	tabTag.on('click','a i',function(e){
		var li = $(this).closest('li');
		var id ;
		var mirror;
		if(li.hasClass('on')){
			li.prev().addClass('on');
			id = li.prev().attr('jid');
			mirror = sideBar.find('a[tid="'+id+'"]');
			setSideOn(mirror);
		}		
		li.remove();
		tabTag.css({'width':tabTag.find('li').length*liw-(tabTag.find('li').length-1)});
		if(tabBox.scrollLeft()>=li.prev().index()*liw){
			tabBox.scrollLeft(tabTag.find('.on').index()*liw);
		}
		if(tabTag.find('li').length==1){
			removeSideOn();
		}		
		e.stopPropagation();
	}).on('click','a',function(){
		var li = $(this).closest('li');
		var id = li.attr('jid');
		var mirror = sideBar.find('a[tid="'+id+'"]');
		if(!li.hasClass('on')){
			mirror.trigger('click');
		}
		if(li.hasClass('home')){
			removeSideOn();
		}else{
			setSideOn(mirror);
		}
		if(tabBox.scrollLeft()+tabBox.width()<(li.index()+1)*liw){
			tabBox.scrollLeft((li.index()+1)*liw-tabBox.width());
		}else if(tabBox.scrollLeft()>li.index()*liw){
			tabBox.scrollLeft(li.index()*liw-li.index());
		}
		li.addClass('on').siblings().removeClass('on');
	});

	flip.find('.prev').click(function(){
		flipTab(-99);
	});
	flip.find('.next').click(function(){
		flipTab(99)
	});
	//flip the tab and reset tabbox' scrollLeft
	function flipTab(dis){
		var l = tabBox.scrollLeft();
		tabBox.scrollLeft(l+dis);
	}
	//remove sidebar's current style
	function removeSideOn(){
		sideBar.find('dl.on dt').trigger('click');
		sideBar.find('.on').removeClass('on');
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
yyyCloud.runroll = function(){
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
}

$(function(){
	yyyCloud.layoutFit();
	yyyCloud.sideBar();
	yyyCloud.runroll();
})

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

