package lingoHigh.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by DuHongcai on 2016/9/18.
 */
public class CharacterEncodingRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    public CharacterEncodingRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {

        try{
            String value = this.request.getParameter(name);
            if (value == null){
                return null;
            }
            if(!request.getMethod().equalsIgnoreCase("get")){
                return value;
            }else {
                value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
                return value;
            }
        }catch (Exception e){
            throw new RuntimeException();
        }

    }
}
