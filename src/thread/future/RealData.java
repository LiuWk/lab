package thread.future;

/**
 * leb测试用源代码
 * 
 * 项目名称 : design_patterns
 * 创建日期 : 2017年7月5日
 * 类  描  述 : //TODO 添加类/接口功能描述
 * 修改历史 : 
 *     1. [2017年7月5日]创建文件 by lwk
 */
public class RealData {

    private String result;

    RealData(String queryStr) {
        System.out.println("数据处理中。。。");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取结果");
        result = "数据AA";
    }

    public String getRequest() {
        return result;
    }
}