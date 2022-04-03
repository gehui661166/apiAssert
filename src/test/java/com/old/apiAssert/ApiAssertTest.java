package com.old.apiAssert;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.check.*;
import com.old.apiAssert.entity.TestEntity;
import com.old.apiAssert.exception.ApiAssertException;
import com.old.apiAssert.exception.NoArgConstructorException;
import org.junit.Test;

import java.util.function.Consumer;

/**
 *
 */
public class ApiAssertTest {

    @Test
    public void testFirstApiAssert() {

        System.out.println();
        try {
            FirstApiAssert apiAssert = FirstApiAssert.create();
            Holder<Boolean> hodler = Holder.holder();
            apiAssert.isNull(new Object(), "asdf")
                    .isEmpty("不为空", "asdfadsf")
                    .isNull(new Object(), "空")
                    .holder(hodler)
                    .ifFail(() -> {
                        System.out.println("此时已经失败");
                    })
                    .ifSuccess(() ->{
                        System.out.println("此时仍无异常");
                    })
                    .handler(flag -> {
                        System.out.println("当前是否出现异常：" + flag);
                    })
                    .handler( (flag, errorMsg) -> {
                        System.out.println("当前有无异常：" + flag + "异常信息为：" + errorMsg);
                    })
                    .failThrow(ApiAssertException::new);
            if (hodler.getData()) {
                System.out.println("此时无异常");
            }
            apiAssert.isNull(null, "对象为空");
            apiAssert.failThrow(ApiAssertException::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOperateApiAssert() {
        TestEntity entity = new TestEntity();
        entity.setId(1);
        OperateApiAssert<TestEntity> apiAssert = OperateApiAssert.create(entity, NoArgConstructorException::new)
                .isNull("");
        apiAssert
                .isEmpty(TestEntity::getId, "id 为空")
                .isTrue(TestEntity::getDeleteFlag, "")
        // .isFalse(TestEntity::getDeleteFlag, "")
        ;
        System.out.println(apiAssert.getClass());
        ((ApiAssert) apiAssert).throwRuntime(new RuntimeException());
        // apiAssert.isNull("", new Object());
    }


    @Test
    public void testFunctionApiAssert() {
        ApiAssert<Object> apiAssert = FunctionApiAssert.create(ApiAssertException::new)
                .isNull(new Object(), "")
                .isEmpty(new Object(), "")
                .isTrue(false, "")
                .isFalse(true, "");
        System.out.println(apiAssert.getClass());
        try {
            ((FunctionApiAssert) apiAssert).throwRuntime(new RuntimeException());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        try {
            apiAssert.isNull(null, "为空");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testReflectionApiAssert() {
        ApiAssert apiAssert = ReflectionApiAssert.create(NoArgConstructorException.class)
                .isNull(new Object(), "")
                .isEmpty(new Object(), "")
                .isTrue(false, "")
                .isFalse(true, "");
        System.out.println(apiAssert.getClass());

        try {
            apiAssert.isTrue(true, "异常");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testApiAssert() {

        System.out.println();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }


        Consumer<Void> consumer = new Consumer<Void>() {
            @Override
            public void accept(Void unused) {

            }
        };
    }

}
