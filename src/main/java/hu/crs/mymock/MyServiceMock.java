package hu.crs.mymock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Optional;

//https://www.kdgregory.com/index.php?page=junit.proxy
public class MyServiceMock implements InvocationHandler {
    private Optional<Integer> one = Optional.empty();
    private Optional<Integer> two = Optional.empty();

    public MyServiceMock stubOne(int value) {
        one = Optional.of(value);
        return this;
    }

    public MyServiceMock stubTwo(int value) {
        two = Optional.of(value);
        return this;
    }

    public MyService build() {
        return (MyService) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{MyService.class}, this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) {
        if (method.getName().equals("one")) {
            return one.orElseThrow(UnsupportedOperationException::new);
        }

        if (method.getName().equals("two")) {
            return two.orElseThrow(UnsupportedOperationException::new);
        }

        throw new UnsupportedOperationException();
    }
}
