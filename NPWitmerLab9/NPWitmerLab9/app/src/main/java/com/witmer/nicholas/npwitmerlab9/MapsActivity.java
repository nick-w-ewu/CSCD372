package com.witmer.nicholas.npwitmerlab9;

import android.app.AlertDialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnLongClickListener
{

    private GoogleMap mMap;
    private LatLng curPosition;
    private float zoom;
    private LocationManager location;
    private int markerCount = 0;
    private int mapType = 1;
    private ArrayList<MarkerOptions> markers;
    private ArrayList<Marker> removableMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.removableMarkers = new ArrayList<Marker>();
        Button addMark = (Button)findViewById(R.id.mark);
        Button type = (Button)findViewById(R.id.type);
        addMark.setTag(1);
        type.setTag(2);
        addMark.setOnLongClickListener(this);
        type.setOnLongClickListener(this);
        if(savedInstanceState == null)
        {
            markers = new ArrayList<MarkerOptions>();
            Toast.makeText(this, "For about long press Change Type button", Toast.LENGTH_SHORT).show();
            this.zoom = 15f;
        }
        else
        {
            this.markers = (ArrayList<MarkerOptions>)savedInstanceState.getSerializable("markers");
            if(this.markers == null)
            {
                this.markers = new ArrayList<MarkerOptions>();
            }
            this.zoom = savedInstanceState.getFloat("zoom");
            this.markerCount = savedInstanceState.getInt("numMarkers");
            this.mapType = savedInstanceState.getInt("type");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle b)
    {
        super.onSaveInstanceState(b);
        b.putSerializable("markers", this.markers);
        b.putFloat("zoom", this.zoom);
        b.putInt("numMarkers", this.markerCount);
        b.putInt("type", this.mMap.getMapType());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        initMap(googleMap);
    }

    public void onPlus(View v)
    {
        mMap.moveCamera(CameraUpdateFactory.zoomIn()) ;
        zoom = mMap.getCameraPosition().zoom ;
    }
    public void onMinus(View v)
    {
        mMap.moveCamera(CameraUpdateFactory.zoomOut()) ;
        zoom = mMap.getCameraPosition().zoom ;
    }

    public void onTypeChange(View v)
    {
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else if(mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else if(mMap.getMapType() == GoogleMap.MAP_TYPE_TERRAIN)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        this.mapType = mMap.getMapType();
    }

    public void onMark(View v)
    {
        location = (LocationManager)getSystemService(this.LOCATION_SERVICE);
        String provider = getProvider(location);
        if(provider == null)
        {
            Toast.makeText(this, "Location unavailable", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Location current = location.getLastKnownLocation(provider);
            double lat = current.getLatitude();
            double lng = current.getLongitude();
            LatLng markerPos = new LatLng(lat, lng);
            markerCount++;
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.pushpin);
            MarkerOptions marker = new MarkerOptions().position(markerPos).title("Mark " + this.markerCount).icon(icon);
            Marker mark = mMap.addMarker(marker);
            this.markers.add(marker);
            this.removableMarkers.add(mark);
        }
    }

    public void initMap(GoogleMap map)
    {
        location = (LocationManager)getSystemService(this.LOCATION_SERVICE);
        String provider = getProvider(location);
        if(provider == null)
        {
            Toast.makeText(this, "Location unavailable", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Marker mark;
            for(MarkerOptions m : this.markers)
            {
                mark = mMap.addMarker(m);
                this.removableMarkers.add(mark);
            }
            Location current = location.getLastKnownLocation(provider);
            double lat = current.getLatitude();
            double lng = current.getLongitude();
            this.curPosition = new LatLng(lat, lng);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(curPosition, zoom);
            map.moveCamera(update);
            mMap.setMapType(this.mapType);
        }
        map.setMyLocationEnabled(true);
    }

    public String getProvider(LocationManager locationMgr)
    {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        String providerName = locationMgr.getBestProvider(criteria, true);
        if (providerName != null)
        {
            return providerName;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean onLongClick(View v)
    {
        if((int)v.getTag() == 1)
        {
            if (this.removableMarkers.size() > 0)
            {
                int last = this.removableMarkers.size() - 1;
                Marker mark = this.removableMarkers.get(last);
                mark.remove();
                this.removableMarkers.remove(last);
                this.markerCount--;
                this.markers.remove(this.markers.size() - 1);
                return true;
            }
        }
        else if((int)v.getTag() == 2)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
            builder.setTitle("About")
                    .setMessage("Nicholas Witmer, CSCD 372, Fall 2015, Lab 9")
                    .setNeutralButton("OK", null)
                    .setCancelable(false)
                    .create()
                    .show();

        }
        return true;
    }
}
