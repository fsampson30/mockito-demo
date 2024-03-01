package com.sampson.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}

class DataServiceStub implements DataService{

    @Override
    public int[] retrieveAllData() {
        return new int[]{25,15,5};
    }
}