package edu.iis.mto.staticmock.reader;

import edu.iis.mto.staticmock.ConfigurationLoader;
import edu.iis.mto.staticmock.NewsReaderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest( ConfigurationLoader.class )
public class NewsLoaderTests {

    @Test
    public void test(){
        mockStatic(ConfigurationLoader.class);
        ConfigurationLoader configurationLoader = mock(ConfigurationLoader.class);
        when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);

        mockStatic(NewsReaderFactory.class);
        NewsReaderFactory newsReaderFactory = mock(NewsReaderFactory.class);
        when(NewsReaderFactory.getReader()).thenReturn(newsReaderFactory);




    }
}
