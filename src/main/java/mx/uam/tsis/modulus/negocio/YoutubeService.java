package mx.uam.tsis.modulus.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Service;
import com.google.api.services.youtube.YouTube;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import mx.uam.tsis.modulus.negocio.dominio.CancionYoutube;

@Service
public class YoutubeService {
	
	private static YouTube youtube;
	
	//Número de Resultados de la Búsqueda
	private static Long numero_videos = (long) 10;
	
	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	//Arreglo de los Videos de la Búsqueda
	private ArrayList<CancionYoutube> video;
	
	
	public YoutubeService() {

	}
	
	
	public ArrayList<CancionYoutube> busca(String cancion){
		try {
			
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
		        public void initialize(HttpRequest request) throws IOException {}
		      }).setApplicationName("Application").build();
			
			YouTube.Search.List search = youtube.search().list("id,snippet");
			
			//Se añade la Llave de Youtube
			search.setKey("AIzaSyCPyKvv56w7nNPywDS9ZcsOnatkB3XL-_s");
			
			//Consulta sobre la canción
			search.setQ(cancion);
			
			//Tipo de Consulta
			search.setType("video");
			
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
		    
			//Número de Elementos de la busqueda
			search.setMaxResults(numero_videos);
			
			//Realiza la Ejecución de la busqueda
		    SearchListResponse searchResponse = search.execute();
		      
		    List<SearchResult> searchResultList = searchResponse.getItems();
		      
		    if (searchResultList != null) {
		    	
		    	//Se obtiene el arreglo de la busqueda
		    	video = convierte(searchResultList.iterator(), cancion);
		    }
		      
		    return video;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	


	private static ArrayList<CancionYoutube> convierte(Iterator<SearchResult> iteratorSearchResults, String query) {
		
		//Variable de Arreglo de la Búsqueda
		ArrayList<CancionYoutube> videos = new ArrayList<CancionYoutube>();

	    if (!iteratorSearchResults.hasNext()) {
	      System.out.println(" No hay resultados en la Busqueda");
	    }

	    while (iteratorSearchResults.hasNext()) {

	      SearchResult singleVideo = iteratorSearchResults.next();
	      ResourceId rId = singleVideo.getId();

	      // Double checks the kind is video.
	      if (rId.getKind().equals("youtube#video")) {
	         
	    	Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().get("default");
	        
	        CancionYoutube aux = new CancionYoutube (rId.getVideoId(),singleVideo.getSnippet().getTitle(),thumbnail.getUrl());
	        
	        videos.add(aux);
	        
	      }
	    }
	    return videos;
	  }

}
