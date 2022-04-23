package hu.crs.mymock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyServiceMockTest {
    @Test
    void canCreateMock() {
        //given
        MyService mock = new MyServiceMock().build();

        //when - then
        assertThrows(UnsupportedOperationException.class, mock::one);
    }

    @Test
    void canCreateMockMockOneMockTwo() {
        //given
        MyService mock = new MyServiceMock()
                .stubOne(3)
                .stubTwo(4)
                .build();

        //when
        assertEquals(mock.one(), 3);
        assertEquals(mock.two(), 4);
    }
}
