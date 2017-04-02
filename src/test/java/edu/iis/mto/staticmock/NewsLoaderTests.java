package edu.iis.mto.staticmock;


import edu.iis.mto.staticmock.reader.NewsReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest( {ConfigurationLoader.class, NewsReaderFactory.class, PublishableNews.class} )
public class NewsLoaderTests {
    private NewsReader newsReader;
    private NewsLoader newsLoader = new NewsLoader();
    private IncomingNews incomingNews = new IncomingNews();
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
    @Test
    public void publicNews(){
        incomingNews.add(new IncomingInfo("Public", SubsciptionType.NONE));
        when(newsReader.read()).thenReturn(incomingNews);

        PublishableNews publishable = newsLoader.loadNews();
        List<String> result = (List<String>)Whitebox.getInternalState(publishable,"publicContent");
        assertThat(result.size(),is(1));
    }

    @Test
    public void subscriptionANews(){
        incomingNews.add(new IncomingInfo("A", SubsciptionType.A));
        when(newsReader.read()).thenReturn(incomingNews);

        PublishableNews publishable = newsLoader.loadNews();
        List<String> result = (List<String>)Whitebox.getInternalState(publishable,"subscribentContent");
        assertThat(result.size(),is(1));
    }
    @Test
    public void subscriptionBNews(){
        incomingNews.add(new IncomingInfo("B", SubsciptionType.A));
        when(newsReader.read()).thenReturn(incomingNews);

        PublishableNews publishable = newsLoader.loadNews();
        List<String> result = (List<String>)Whitebox.getInternalState(publishable,"subscribentContent");
        assertThat(result.size(),is(1));
    }
    @Test
    public void subscriptionCNews(){
        incomingNews.add(new IncomingInfo("C", SubsciptionType.A));
        when(newsReader.read()).thenReturn(incomingNews);

        PublishableNews publishable = newsLoader.loadNews();
        List<String> result = (List<String>)Whitebox.getInternalState(publishable,"subscribentContent");
        assertThat(result.size(),is(1));
    }
}

