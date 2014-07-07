 package com.runtraining;



import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;









import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;



public class maps extends FragmentActivity implements OnMapClickListener{
	
	
	public GoogleMap mapa;
	double latitudNueva,longitudNueva;
	double latitudAnterior=0,longitudAnterior=0;
	boolean empezar=false;
	int contador_id=1;
	CountDownTimer timer;
	double calculo,valorBaseDatos=0;
	
	int sigueAsi,masRapido;
	SoundManager sonido;
	
	double [] datos={50,1,2,5,10,30,50,110,20,1,2,5,10,30,50,110,20,1,2,5,10,30,50,110,20};
		
	
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
    	   	
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.maps);
        Button botEmpezar = (Button)findViewById(R.id.botonEmpezar);
        final Button botonAcabar = (Button)findViewById(R.id.botonFinalizar);
        final TextView entrena = (TextView)findViewById(R.id.distanciaRecorrida);
        final TextView cuentaAtras = (TextView)findViewById(R.id.cuentaAtras);
        final TextView objetivo = (TextView)findViewById(R.id.objetivo2);
        
        
        mapa = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setCompassEnabled(true);
        mapa.getUiSettings().setAllGesturesEnabled(true);
        CameraUpdate Zoom = CameraUpdateFactory.zoomTo(19);
        mapa.animateCamera(Zoom);
        sonido=new SoundManager(getApplicationContext());
        masRapido= sonido.load(R.raw.masrapido);
        sigueAsi= sonido.load(R.raw.sigueasi);
        
        
        
        
        
        mapa.setOnMyLocationChangeListener(new OnMyLocationChangeListener() {
			
			@Override
			public void onMyLocationChange(Location pos) {
				
				latitudNueva=pos.getLatitude();
				longitudNueva=pos.getLongitude();
					
	     		
				LatLng latlng = new LatLng(latitudNueva,longitudNueva);
				
				mapa.moveCamera(CameraUpdateFactory.newLatLng(latlng));
				mapa.animateCamera(CameraUpdateFactory.zoomTo(19));
					
				if(empezar){
					LatLng pos2= new LatLng(latitudNueva+0.0000009, longitudNueva+0.0000009);
					
					CircleOptions opcionesCirculo = new CircleOptions().center(pos2).radius(2);
					Circle circulo = mapa.addCircle(opcionesCirculo);
					circulo.setFillColor(Color.GREEN);
					circulo.setStrokeColor(Color.GREEN);
					circulo.setStrokeWidth(2f);
					
					
					
				}
				
			}	
			
		});
        
        
        
        
        
        
        if(!empezar){
        	entrena.setText("Entrenamiento parado");
        	entrena.setTextColor(Color.BLUE);
        	cuentaAtras.setTextColor(Color.TRANSPARENT);
        	objetivo.setTextColor(Color.TRANSPARENT);
        	botonAcabar.setVisibility(View.INVISIBLE);
        }
        
        
        
       
        
        
        
        
    	botonAcabar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				empezar=false;
				entrena.setText("Entrenamiento parado");
	        	entrena.setTextColor(Color.BLACK);
	        	cuentaAtras.setTextColor(Color.TRANSPARENT);
	        	objetivo.setTextColor(Color.TRANSPARENT);
	        	botonAcabar.setVisibility(View.INVISIBLE);
	        	mapa.clear();
	        	timer.cancel();
			}
		});
        
    	
    	
    	
    	
    	
    	
    	
        
        botEmpezar.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(!empezar){
					latitudAnterior=latitudNueva;
   		     		longitudAnterior=longitudNueva;
				}
				empezar=true;
				entrena.setText("ENTRENANDO!!");
	        	entrena.setTextColor(Color.BLUE);
	        	cuentaAtras.setTextColor(Color.BLACK);
	        	botonAcabar.setVisibility(View.VISIBLE);
	        	
	        	timer = new CountDownTimer(30000, 1000) {
	   			
	        		public void onTick(long millisUntilFinished) {
	   		    	 TextView cuentaAtras = (TextView)findViewById(R.id.cuentaAtras);
	   		    	 cuentaAtras.setText("Tiempo: " + millisUntilFinished/1000 + " seg.");         
	   		     	}
	        		int i=0;
	   		     	public void onFinish() {  	
	   		     		calculo=calcularDistancia(latitudNueva,longitudNueva,latitudAnterior,longitudAnterior);
	   		     		calculo=calculo*1000;
	   		     		calculo=redondear(calculo, 3);
	   		     		entrena.setText("Distancia Recorrida: "+calculo+" m");
	   		     		objetivo.setTextColor(Color.BLUE);
	   		     		objetivo.setText("Objetivo a superar: "+datos[i]+ " m");
   		     			reproducir(i);
	   		     		i++;
	   		     		latitudAnterior=latitudNueva;
	   		     		longitudAnterior=longitudNueva;
	   		     		timer.start();
	   		     	}
	   		     	public void reproducir(int i){
		   		     	if(datos[i]<=calculo)
	   		     			sonido.play(sigueAsi);
	   		     		else
	   		     			sonido.play(masRapido);
	   		     	}    
	   		  }.start();		
		  	}	
		});   
        
        
    }  
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.m_maps, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.about:
			Toast.makeText(maps.this, "RunTraining: Realizado por Javier Aranda y Angel Jiménez de Cisneros", Toast.LENGTH_LONG).show();
			return true;
			
		case R.id.quit:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
    
  

	public double redondear( double numero, int decimales ) {
	    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	}



	public double calcularDistancia(double lat1,double lon1,double lat2,double lon2){
    	
    	double R=6378.137;
    	double DLat=(lat2-lat1)*Math.PI/180;
    	double DLon=(lon2-lon1)*Math.PI/180;
    	
    	double a = Math.sin(DLat/2) * Math.sin(DLat/2) + Math.cos((lat1)*Math.PI/180) * Math.cos((lat2)*Math.PI/180) * Math.sin(DLon/2) * Math.sin(DLon/2);
    	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    	double d = R * c;
    	
    	
		return d;
    	
    	
    }
    


	@Override
	public void onMapClick(LatLng point) {
		// TODO Auto-generated method stub
		
	}
    
    
  }