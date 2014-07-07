package com.runtraining;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton botonEntrena=(ImageButton)findViewById(R.id.EntrenamientoBoton);
		botonEntrena.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,maps.class);
				startActivity(i);
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.about:
			Toast.makeText(MainActivity.this, "RunTraining: Realizado por Javier Aranda y Angel Jiménez de Cisneros", Toast.LENGTH_LONG).show();
			return true;
			
		case R.id.quit:
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			LayoutInflater inflater = getLayoutInflater();
			dialog.setMessage("¿Desea salir de RunTraining?");
			dialog.setCancelable(false);
			dialog.setView(inflater.inflate(R.layout.dialog, null));
			dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					MainActivity.this.finish();
				}
			});
			dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			dialog.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	

}


