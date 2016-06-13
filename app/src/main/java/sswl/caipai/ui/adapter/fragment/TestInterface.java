package sswl.caipai.ui.adapter.fragment;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class TestInterface {
   private String message = "message";
    See see;
    public TestInterface(See see){
        this.see = see;
    }
    public interface See{
        void info(String info);
    }
}
