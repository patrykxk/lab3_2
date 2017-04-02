package edu.iis.mto.staticmock;


import edu.iis.mto.staticmock.reader.NewsReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.internal.util.reflection.Whitebox;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest( {ConfigurationLoader.class, NewsReaderFactory.class, PublishableNews.class} )
public class NewsLoaderTests {
    private NewsReader newsReader;
    private NewsLoader newsLoader = new NewsLoader();

    @Before
    public void setUp(){
        String readerType = "test";

        mockStatic(ConfigurationLoader.class);
        ConfigurationLoader configurationLoader = mock(ConfigurationLoader.class);
        when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);

		Configuration configuration = new Configuration();
		Whitebox.setInternalState(configuration, "readerType", readerType);
		when(configurationLoader.loadConfiguration()).thenReturn(configuration);


        mockStatic(NewsReaderFactory.class);
        newsReader = mock(NewsReader.class);
        when(NewsReaderFactory.getReader(readerType)).thenReturn(newsReader);

        mockStatic(PublishableNews.class);
        when(PublishableNews.create()).thenReturn(new PublishableNews());


    }

}
