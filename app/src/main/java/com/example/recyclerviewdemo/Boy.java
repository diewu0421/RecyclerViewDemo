package com.example.recyclerviewdemo;


/**
 * 浙江集商优选电子商务有限公司
 *
 * @author zenglw
 * @date 2021/1/20 7:17 下午
 */

public class Boy {

    public String boyName;
    public Girl girl;

    public class Girl {
        public String girlName;
    }

//    public static void main(String[] args) {
//        test();
//    }
//    private static void test(){
//
//        Gson gson = new Gson();
//        String boyJsonStr = "{\"boyName\":\"zhy\",\"girl\":{\"girlName\":\"lmj\"}}";
//        Boy boy = gson.fromJson(boyJsonStr, Boy.class);
//        System.out.println("boy name is = " + boy.boyName + " , girl name is = " + boy.girl.girlName);
//        // 新增
//        System.out.println(boy.girl.girlName);
//    }
}
