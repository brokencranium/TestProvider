package com.bignerdranch.android.testprovider;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;

import java.util.ArrayList;

public class DebugRun {
    private static DebugRun instance;

    private static final String testProvider = "TEST_PROVIDER";

    public static DebugRun getInstance(Context c) {
        if (instance == null) {
            instance = new DebugRun(c);
        }

        return instance;
    }

    public interface Listener {
        public void onLocationChanged(Location location);
        public void onProviderChanged();
    }

    private Context context;
    private ArrayList<Listener> listeners = new ArrayList<Listener>();
    private ArrayList<Location> locations;
    private LocationManager locationManager;
    private DebugRun(Context c) {
        context = c.getApplicationContext();
        initLocationData();
        locationManager = (LocationManager)
            context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void addListener(Listener listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public boolean doesHasTestProvider() {
        return locationManager.getProvider(testProvider) != null;
    }


    public void turnOnTestProvider() {
        if (doesHasTestProvider()) {
            locationManager.removeTestProvider(testProvider);
        }
        locationManager.addTestProvider(
                testProvider, 
                false, 
                false, 
                false, 
                false, 
                true, 
                false, 
                false, 
                0, 
                0);
        locationManager.setTestProviderEnabled(testProvider, true);
        locationManager.setTestProviderStatus(testProvider, GpsStatus.GPS_EVENT_STARTED, null, System.currentTimeMillis());
        for (Listener listener : listeners) {
            listener.onProviderChanged();
        }
        Intent i = new Intent(context, TestProviderService.class);
        context.startService(i);
    }

    public void turnOffTestProvider() {
        locationManager.removeTestProvider(testProvider);
        for (Listener listener : listeners) {
            listener.onProviderChanged();
        }
        Intent i = new Intent(context, TestProviderService.class);
        context.stopService(i);
    }

    public int size() {
        return locations.size();
    }

    public void publishLocation(int i) {
        i = (i > 0 ? i : -i) % size();
        Location l = new Location(locations.get(i));
        l.setTime(System.currentTimeMillis());
        locationManager.setTestProviderLocation(testProvider, l);
        for (Listener listener : listeners) {
            listener.onLocationChanged(l);
        }
    }

    private Location newLocation(double lat, double lon, double alt) {
        Location l = new Location(testProvider);
        l.setLatitude(lat);
        l.setLongitude(lon);
        l.setAltitude(alt);

        return l;
    }

    private void initLocationData() {
        locations = new ArrayList<Location>();
        locations.add(newLocation(-122.335919,47.581091,0));
        locations.add(newLocation(-122.334894,47.580774,0));
        locations.add(newLocation(-122.334199,47.580766,0));
        locations.add(newLocation(-122.325975,47.571401,0));
        locations.add(newLocation(-122.334194,47.575295,0));
        locations.add(newLocation(-122.334205,47.57447299999999,0));
        locations.add(newLocation(-122.33429,47.574395,0));
        locations.add(newLocation(-122.334335,47.57392200000001,0));
        locations.add(newLocation(-122.334303,47.57144899999999,0));
        locations.add(newLocation(-122.333983,47.571414,0));
        locations.add(newLocation(-122.332046,47.571386,0));
        locations.add(newLocation(-122.328975,47.571426,0));
        locations.add(newLocation(-122.325188,47.571201,0));
        locations.add(newLocation(-122.324592,47.57109999999999,0));
        locations.add(newLocation(-122.321502,47.571102,0));
        locations.add(newLocation(-122.321182,47.571001,0));
        locations.add(newLocation(-122.320785,47.570786,0));
        locations.add(newLocation(-122.320604,47.57061400000001,0));
        locations.add(newLocation(-122.320494,47.570437,0));
        locations.add(newLocation(-122.320429,47.570173,0));
        locations.add(newLocation(-122.32044,47.569762,0));
        locations.add(newLocation(-122.320374,47.56961399999999,0));
        locations.add(newLocation(-122.320456,47.569137,0));
        locations.add(newLocation(-122.32194,47.562138,0));
        locations.add(newLocation(-122.32207,47.560928,0));
        locations.add(newLocation(-122.322002,47.559938,0));
        locations.add(newLocation(-122.321762,47.558998,0));
        locations.add(newLocation(-122.321159,47.557733,0));
        locations.add(newLocation(-122.320908,47.557344,0));
        locations.add(newLocation(-122.319478,47.555484,0));
        locations.add(newLocation(-122.317725,47.552601,0));
        locations.add(newLocation(-122.317248,47.551907,0));
        locations.add(newLocation(-122.316574,47.551195,0));
        locations.add(newLocation(-122.315996,47.550742,0));
        locations.add(newLocation(-122.313576,47.549298,0));
        locations.add(newLocation(-122.310819,47.547719,0));
        locations.add(newLocation(-122.309794,47.547021,0));
        locations.add(newLocation(-122.308869,47.546281,0));
        locations.add(newLocation(-122.307567,47.545006,0));
        locations.add(newLocation(-122.305939,47.543298,0));
        locations.add(newLocation(-122.303381,47.540768,0));
        locations.add(newLocation(-122.301869,47.539452,0));
        locations.add(newLocation(-122.29825,47.536492,0));
        locations.add(newLocation(-122.297262,47.535545,0));
        locations.add(newLocation(-122.29661,47.534634,0));
        locations.add(newLocation(-122.292831,47.526976,0));
        locations.add(newLocation(-122.292258,47.525658,0));
        locations.add(newLocation(-122.29182,47.524469,0));
        locations.add(newLocation(-122.291359,47.52298399999999,0));
        locations.add(newLocation(-122.290746,47.521386,0));
        locations.add(newLocation(-122.288725,47.517269,0));
        locations.add(newLocation(-122.28842,47.51672400000001,0));
        locations.add(newLocation(-122.287755,47.515719,0));
        locations.add(newLocation(-122.28001,47.505577,0));
        locations.add(newLocation(-122.278729,47.50407300000001,0));
        locations.add(newLocation(-122.277715,47.503011,0));
        locations.add(newLocation(-122.275626,47.500957,0));
        locations.add(newLocation(-122.272286,47.497794,0));
        locations.add(newLocation(-122.2704,47.496127,0));
        locations.add(newLocation(-122.266567,47.493005,0));
        locations.add(newLocation(-122.265546,47.492118,0));
        locations.add(newLocation(-122.265181,47.491691,0));
        locations.add(newLocation(-122.264855,47.49118299999999,0));
        locations.add(newLocation(-122.264648,47.49060899999999,0));
        locations.add(newLocation(-122.264567,47.490017,0));
        locations.add(newLocation(-122.264609,47.489498,0));
        locations.add(newLocation(-122.264717,47.489115,0));
        locations.add(newLocation(-122.264891,47.48871400000001,0));
        locations.add(newLocation(-122.265232,47.488182,0));
        locations.add(newLocation(-122.26592,47.487498,0));
        locations.add(newLocation(-122.269235,47.484987,0));
        locations.add(newLocation(-122.271584,47.483026,0));
        locations.add(newLocation(-122.271956,47.482598,0));
        locations.add(newLocation(-122.272409,47.481888,0));
        locations.add(newLocation(-122.272658,47.481235,0));
        locations.add(newLocation(-122.272757,47.480612,0));
        locations.add(newLocation(-122.27271,47.479972,0));
        locations.add(newLocation(-122.272631,47.479627,0));
        locations.add(newLocation(-122.272674,47.479559,0));
        locations.add(newLocation(-122.272402,47.478899,0));
        locations.add(newLocation(-122.270606,47.4758,0));
        locations.add(newLocation(-122.269878,47.474386,0));
        locations.add(newLocation(-122.269557,47.473597,0));
        locations.add(newLocation(-122.269191,47.472418,0));
        locations.add(newLocation(-122.268053,47.46811,0));
        locations.add(newLocation(-122.267521,47.46656,0));
        locations.add(newLocation(-122.267059,47.465385,0));
        locations.add(newLocation(-122.265316,47.461876,0));
        locations.add(newLocation(-122.264834,47.460716,0));
        locations.add(newLocation(-122.26439,47.459008,0));
        locations.add(newLocation(-122.264234,47.45773699999999,0));
        locations.add(newLocation(-122.264261,47.456081,0));
        locations.add(newLocation(-122.264393,47.455072,0));
        locations.add(newLocation(-122.264575,47.45423699999999,0));
        locations.add(newLocation(-122.26483,47.453418,0));
        locations.add(newLocation(-122.266881,47.448264,0));
        locations.add(newLocation(-122.26753,47.44642,0));
        locations.add(newLocation(-122.267943,47.4449,0));
        locations.add(newLocation(-122.270101,47.435013,0));
        locations.add(newLocation(-122.270416,47.43380899999999,0));
        locations.add(newLocation(-122.270771,47.433015,0));
        locations.add(newLocation(-122.271194,47.43233200000001,0));
        locations.add(newLocation(-122.271874,47.43150199999999,0));
        locations.add(newLocation(-122.272704,47.430708,0));
        locations.add(newLocation(-122.273609,47.430026,0));
        locations.add(newLocation(-122.275811,47.428718,0));
        locations.add(newLocation(-122.277048,47.427932,0));
        locations.add(newLocation(-122.284252,47.422855,0));
        locations.add(newLocation(-122.286377,47.421256,0));
        locations.add(newLocation(-122.28726,47.420455,0));
        locations.add(newLocation(-122.287951,47.419719,0));
        locations.add(newLocation(-122.288796,47.418631,0));
        locations.add(newLocation(-122.289252,47.417908,0));
        locations.add(newLocation(-122.289735,47.417032,0));
        locations.add(newLocation(-122.290063,47.416226,0));
        locations.add(newLocation(-122.292064,47.408556,0));
        locations.add(newLocation(-122.292321,47.407253,0));
        locations.add(newLocation(-122.292529,47.405622,0));
        locations.add(newLocation(-122.292641,47.403979,0));
        locations.add(newLocation(-122.292622,47.402332,0));
        locations.add(newLocation(-122.292494,47.401128,0));
        locations.add(newLocation(-122.290989,47.391142,0));
        locations.add(newLocation(-122.290538,47.38781399999999,0));
        locations.add(newLocation(-122.290396,47.386142,0));
        locations.add(newLocation(-122.290374,47.38515300000001,0));
        locations.add(newLocation(-122.290521,47.38279000000001,0));
        locations.add(newLocation(-122.290778,47.38129300000001,0));
        locations.add(newLocation(-122.29122,47.379709,0));
        locations.add(newLocation(-122.291656,47.377622,0));
        locations.add(newLocation(-122.295354,47.363394,0));
        locations.add(newLocation(-122.29569,47.362275,0));
        locations.add(newLocation(-122.296627,47.358536,0));
        locations.add(newLocation(-122.296793,47.357567,0));
        locations.add(newLocation(-122.296828,47.357005,0));
        locations.add(newLocation(-122.296836,47.356211,0));
        locations.add(newLocation(-122.296749,47.355161,0));
        locations.add(newLocation(-122.296579,47.35412899999999,0));
        locations.add(newLocation(-122.296349,47.353226,0));
        locations.add(newLocation(-122.295872,47.351872,0));
        locations.add(newLocation(-122.294407,47.34894799999999,0));
        locations.add(newLocation(-122.29371,47.347446,0));
        locations.add(newLocation(-122.293324,47.346167,0));
        locations.add(newLocation(-122.293062,47.344586,0));
        locations.add(newLocation(-122.29302,47.343656,0));
        locations.add(newLocation(-122.293053,47.342623,0));
        locations.add(newLocation(-122.294563,47.324893,0));
        locations.add(newLocation(-122.294953,47.32266,0));
        locations.add(newLocation(-122.295441,47.320948,0));
        locations.add(newLocation(-122.297128,47.316768,0));
        locations.add(newLocation(-122.298267,47.31378,0));
        locations.add(newLocation(-122.30053,47.308317,0));
        locations.add(newLocation(-122.30097,47.30712900000001,0));
        locations.add(newLocation(-122.309294,47.282466,0));
        locations.add(newLocation(-122.310372,47.280236,0));
        locations.add(newLocation(-122.311051,47.279032,0));
        locations.add(newLocation(-122.313977,47.274326,0));
        locations.add(newLocation(-122.314609,47.273415,0));
        locations.add(newLocation(-122.315972,47.27161,0));
        locations.add(newLocation(-122.317146,47.270272,0));
        locations.add(newLocation(-122.318918,47.268516,0));
        locations.add(newLocation(-122.327784,47.260364,0));
        locations.add(newLocation(-122.328984,47.259078,0));
        locations.add(newLocation(-122.329827,47.258033,0));
        locations.add(newLocation(-122.330582,47.256959,0));
        locations.add(newLocation(-122.331208,47.25592200000001,0));
        locations.add(newLocation(-122.331684,47.255,0));
        locations.add(newLocation(-122.332312,47.253522,0));
        locations.add(newLocation(-122.332719,47.252243,0));
        locations.add(newLocation(-122.33441,47.245948,0));
        locations.add(newLocation(-122.334752,47.245095,0));
        locations.add(newLocation(-122.335355,47.24418900000001,0));
        locations.add(newLocation(-122.335882,47.243635,0));
        locations.add(newLocation(-122.336275,47.243298,0));
        locations.add(newLocation(-122.336936,47.242834,0));
        locations.add(newLocation(-122.337538,47.24249,0));
        locations.add(newLocation(-122.338641,47.242015,0));
        locations.add(newLocation(-122.339517,47.241743,0));
        locations.add(newLocation(-122.34022,47.24158199999999,0));
        locations.add(newLocation(-122.341079,47.241448,0));
        locations.add(newLocation(-122.342013,47.241374,0));
        locations.add(newLocation(-122.359992,47.240875,0));
        locations.add(newLocation(-122.371147,47.240661,0));
        locations.add(newLocation(-122.374343,47.240682,0));
        locations.add(newLocation(-122.376436,47.240743,0));
        locations.add(newLocation(-122.378858,47.24088699999999,0));
        locations.add(newLocation(-122.394409,47.242011,0));
        locations.add(newLocation(-122.395532,47.242048,0));
        locations.add(newLocation(-122.396617,47.241987,0));
        locations.add(newLocation(-122.397683,47.241827,0));
        locations.add(newLocation(-122.398779,47.24153600000001,0));
        locations.add(newLocation(-122.402277,47.240186,0));
        locations.add(newLocation(-122.403053,47.23993,0));
        locations.add(newLocation(-122.403859,47.23975000000001,0));
        locations.add(newLocation(-122.404967,47.239602,0));
        locations.add(newLocation(-122.410367,47.239227,0));
        locations.add(newLocation(-122.411769,47.23909900000001,0));
        locations.add(newLocation(-122.416613,47.238581,0));
        locations.add(newLocation(-122.417681,47.238409,0));
        locations.add(newLocation(-122.418602,47.238209,0));
        locations.add(newLocation(-122.419737,47.237902,0));
        locations.add(newLocation(-122.420723,47.237569,0));
        locations.add(newLocation(-122.427063,47.234728,0));
        locations.add(newLocation(-122.427915,47.234379,0));
        locations.add(newLocation(-122.428776,47.234095,0));
        locations.add(newLocation(-122.4297,47.23384,0));
        locations.add(newLocation(-122.430822,47.23362100000001,0));
        locations.add(newLocation(-122.431589,47.233513,0));
        locations.add(newLocation(-122.435145,47.23315,0));
        locations.add(newLocation(-122.436611,47.232908,0));
        locations.add(newLocation(-122.43767,47.232636,0));
        locations.add(newLocation(-122.440645,47.23169800000001,0));
        locations.add(newLocation(-122.44311,47.230988,0));
        locations.add(newLocation(-122.444276,47.230724,0));
        locations.add(newLocation(-122.446072,47.230452,0));
        locations.add(newLocation(-122.44718,47.23036,0));
        locations.add(newLocation(-122.448936,47.230292,0));
        locations.add(newLocation(-122.450861,47.230422,0));
        locations.add(newLocation(-122.456964,47.231138,0));
        locations.add(newLocation(-122.458148,47.23113500000001,0));
        locations.add(newLocation(-122.459044,47.231033,0));
        locations.add(newLocation(-122.46005,47.230784,0));
        locations.add(newLocation(-122.461105,47.23035700000001,0));
        locations.add(newLocation(-122.461826,47.229921,0));
        locations.add(newLocation(-122.46253,47.229314,0));
        locations.add(newLocation(-122.462782,47.22902500000001,0));
        locations.add(newLocation(-122.463075,47.228583,0));
        locations.add(newLocation(-122.463268,47.228177,0));
        locations.add(newLocation(-122.4634,47.227729,0));
        locations.add(newLocation(-122.463471,47.22685,0));
        locations.add(newLocation(-122.463534,47.22148,0));
        locations.add(newLocation(-122.463436,47.219758,0));
        locations.add(newLocation(-122.461454,47.20429000000001,0));
        locations.add(newLocation(-122.461414,47.203716,0));
        locations.add(newLocation(-122.461455,47.202926,0));
        locations.add(newLocation(-122.462302,47.197441,0));
        locations.add(newLocation(-122.462421,47.195764,0));
        locations.add(newLocation(-122.462511,47.19118799999999,0));
        locations.add(newLocation(-122.462651,47.18998499999999,0));
        locations.add(newLocation(-122.462856,47.189042,0));
        locations.add(newLocation(-122.465831,47.17829099999999,0));
        locations.add(newLocation(-122.466167,47.177348,0));
        locations.add(newLocation(-122.466503,47.176712,0));
        locations.add(newLocation(-122.46705,47.175925,0));
        locations.add(newLocation(-122.467481,47.175435,0));
        locations.add(newLocation(-122.475956,47.167026,0));
        locations.add(newLocation(-122.476236,47.166924,0));
        locations.add(newLocation(-122.47779,47.16557699999999,0));
        locations.add(newLocation(-122.478235,47.165276,0));
        locations.add(newLocation(-122.478939,47.164942,0));
        locations.add(newLocation(-122.480639,47.164445,0));
        locations.add(newLocation(-122.480926,47.16431,0));
        locations.add(newLocation(-122.481362,47.16400299999999,0));
        locations.add(newLocation(-122.481407,47.163929,0));
        locations.add(newLocation(-122.48143,47.163759,0));
        locations.add(newLocation(-122.481333,47.16349999999999,0));
        locations.add(newLocation(-122.464592,47.159288,0));
        locations.add(newLocation(-122.463392,47.159029,0));
        locations.add(newLocation(-122.461472,47.158717,0));
        locations.add(newLocation(-122.459544,47.158519,0));
        locations.add(newLocation(-122.457769,47.158435,0));
        locations.add(newLocation(-122.440321,47.158228,0));
        locations.add(newLocation(-122.440071,47.158169,0));
        locations.add(newLocation(-122.437141,47.158025,0));
        locations.add(newLocation(-122.435782,47.15787399999999,0));
        locations.add(newLocation(-122.434218,47.157856,0));
        locations.add(newLocation(-122.434578,47.14063299999999,0));
    }
}
