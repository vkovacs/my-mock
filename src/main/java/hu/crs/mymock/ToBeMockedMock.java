package hu.crs.mymock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Optional;

//https://www.kdgregory.com/index.php?page=junit.proxy
public class ToBeMockedMock implements InvocationHandler {
    private Optional<Integer> one = Optional.empty();
    private Optional<Integer> two = Optional.empty();

    public ToBeMockedMock stubOne(int value) {
        one = Optional.of(value);
        return this;
    }

    public ToBeMockedMock stubTwo(int value) {
        two = Optional.of(value);
        return this;
    }

    public ToBeMocked stub() {
        return (ToBeMocked) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{ToBeMocked.class}, this);
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
