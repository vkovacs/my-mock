package hu.crs.mymock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyMockTest {
    @Test
    void canCreateMock() {
        //given
        ToBeMocked mock = new ToBeMockedMock().stub();

        //when - then
        assertThrows(UnsupportedOperationException.class, mock::one);
    }

    @Test
    void canCreateMockMockOneMockTwo() {
        //given
        ToBeMocked mock = new ToBeMockedMock()
                .stubOne(3)
                .stubTwo(4)
                .stub();

        //when
        assertEquals(mock.one(), 3);
        assertEquals(mock.two(), 4);
    }
}
