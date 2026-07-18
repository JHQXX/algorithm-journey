package functional;

public class MyStream {
    private String content;

    private MyStream(String content) {
        this.content = content;
    }

    // 静态工厂方法，开启流
    public static MyStream of(String content) {
        return new MyStream(content);
    }

    // 每一个操作都返回 this，从而支持 .链式调用
    public MyStream toUpper() {
        this.content = this.content.toUpperCase();
        return this; 
    }

    public MyStream addSuffix(String suffix) {
        this.content = this.content + suffix;
        return this;
    }

    // 终端操作，获取最终结果
    public String get() {
        return this.content;
    }

    public static void main(String[] args) {
        String result = MyStream.of("hello")
                                .toUpper()
                                .addSuffix(" world")
                                .get();
        System.out.println(result); // 输出: HELLO world
    }
}