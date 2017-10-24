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
public class FutureClient {
    public Data request(final String queryStr) {
        final FutureData data = new FutureData();
        new Thread(new Runnable() {

            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                data.setRealData(realData);
            }
        }).start();
        return data;
    }
}
