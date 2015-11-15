package com.bighi.algos.week4;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Graph.class, SCCComputer.class})
public class SCCComputerTest {

    @Test
    public void testSCCSmallData1() throws IOException {
        Integer[] expectedOutput = new Integer[] { 3, 3, 3, 0, 0 };
        List<Integer> actualOutput = new SCCComputer()
                .getTop5Sccs("week4/small1.txt");
        Assert.assertArrayEquals(expectedOutput, actualOutput.toArray());
    }

    @Test
    public void testSCCSmallData2() throws IOException {
        Integer[] expectedOutput = new Integer[] { 3, 3, 2, 0, 0 };
        List<Integer> actualOutput = new SCCComputer()
                .getTop5Sccs("week4/small2.txt");
        Assert.assertArrayEquals(expectedOutput, actualOutput.toArray());
    }

    @Test
    public void testToVerifyCallsToDFSloop() throws Exception {
        SCCComputer sCCComputer = spy(new SCCComputer());
        // SCCComputer sCCComputer = mock(SCCComputer.class);

        Graph graphMock = mock(Graph.class);
        PowerMockito.whenNew(Graph.class).withNoArguments().thenReturn(graphMock);
        
        Node nodeMock = mock(Node.class);
        Edge edgeMock = mock(Edge.class);
        
        doNothing().when(sCCComputer).dfsLoop(any(Graph.class), anyBoolean(),
                anyListOf(Integer.class));
        doNothing().when(graphMock).reverseEdgeDirections();
        doNothing().when(graphMock).unMarkExploredAllNodes();
        doNothing().when(graphMock).switchValuesAndFinishingTimeInNodes();
        doReturn(nodeMock).when(graphMock).addNode(anyInt());
        
        PowerMockito.whenNew(Edge.class).withAnyArguments().thenReturn(edgeMock);
        
        doReturn(edgeMock).when(graphMock).addEdge(any(Edge.class));
        doNothing().when(nodeMock).addIncomingEdge(any(Edge.class));
        doNothing().when(nodeMock).addOutgoingEdge(any(Edge.class));
        
        sCCComputer.getTop5Sccs("week4/small2.txt");
        
        verify(sCCComputer, times(2)).dfsLoop(any(Graph.class), anyBoolean(),
                anyListOf(Integer.class));
        verify(graphMock, times(2)).reverseEdgeDirections();
        verify(graphMock).unMarkExploredAllNodes();
        verify(graphMock).switchValuesAndFinishingTimeInNodes();
        verify(graphMock, atLeast(1)).addEdge(any(Edge.class));
        verify(graphMock, atLeast(1)).addNode(anyInt());
        verify(nodeMock, atLeast(1)).addIncomingEdge(any(Edge.class));
        verify(nodeMock, atLeast(1)).addOutgoingEdge(any(Edge.class));        
    }

}
