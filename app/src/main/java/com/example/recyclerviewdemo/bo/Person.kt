package com.example.recyclerviewdemo.bo

/**
 * 浙江集商优选电子商务有限公司
 * @author zenglw
 * @date   21-1-20 上午9:54
 */
data class Person(val name: String, val age: Int) {

    init {
        println("constructor")
    }
}
fun main(){
//    Person("asdfaf", 23)
//
//    runCatching {
//
//        Class.forName("sun.misc.Unsafe").let {cls->
//            cls.getDeclaredField("theUnsafe").let {field->
//                field.isAccessible = true
//                val theUnsafe = field.get(null)
//                theUnsafe
//
//            }?.let {theUnsafe ->
//                cls.getMethod("allocateInstance", Class::class.java)
//                    .invoke(theUnsafe, Person::class.java).let {person->
//                        println("person = $person")
//                    }
//            }
//        }
//    }.onFailure {
//        it.printStackTrace()
//    }

//    val gson = Gson()
//    val boyJsonStr = "{\"boyName\":\"zhy\",\"gril\":{\"girlName\":\"lmj\"}}"
//    val boy: Boy = gson.fromJson(boyJsonStr, Boy::class.java)
//    System.out.println("boy name is = " + boy.boyName.toString() + " , girl name is = " + boy.gril.girlName)
//     新增
//     新增
//    System.out.println(boy.gril.getBoyName())
}