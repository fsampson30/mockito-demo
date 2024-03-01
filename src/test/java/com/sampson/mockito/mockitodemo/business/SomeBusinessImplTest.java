package com.sampson.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplTest {

    @Mock
    private DataService dataServiceMock;

    @InjectMocks
    private SomeBusinessImpl business;


    @Test
    void findTheGreatestFromAllData() {
        DataServiceStub dataServiceStub = new DataServiceStub();
        SomeBusinessImpl business = new SomeBusinessImpl(dataServiceStub);
        int result = business.findTheGreatestFromAllData();
        assertEquals(25,result);
    }

    @Test
    void findTheGreatestFromAllDataMock(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25,15,3});
        assertEquals(25, business.findTheGreatestFromAllData());
    }


    @Test
    void findTheGreatestFromAllDataOneValueMock(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25});
        assertEquals(25, business.findTheGreatestFromAllData());
    }

    @Test
    void listMockingTest(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(3);
        assertEquals(3,listMock.size());
    }

    @Test
    void listMockingParametersTest(){
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("SomeString");
        assertEquals("SomeString",listMock.get(0));
    }

    @Test
    void listMockingGenericParametersTest(){
        List listMock = mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");
        assertEquals("SomeOtherString",listMock.get(99));
    }

}

class DataServiceStub implements DataService{

    @Override
    public int[] retrieveAllData() {
        return new int[]{25,15,5};
    }
}