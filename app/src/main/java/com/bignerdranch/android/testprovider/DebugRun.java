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

        locations.add(newLocation(	47.457737	,	-122.26423	,	30));
        locations.add(newLocation(	47.456081	,	-122.26426	,	30));
        locations.add(newLocation(	47.459008	,	-122.2643	,	30));
        locations.add(newLocation(	47.455072	,	-122.26439	,	30));
        locations.add(newLocation(	47.490017	,	-122.26456	,	28));
        locations.add(newLocation(	47.454237	,	-122.26457	,	28));
        locations.add(newLocation(	47.489498	,	-122.2646	,	28));
        locations.add(newLocation(	47.490609	,	-122.26464	,	28));
        locations.add(newLocation(	47.489115	,	-122.26471	,	28));
        locations.add(newLocation(	47.453418	,	-122.2648	,	28));
        locations.add(newLocation(	47.460716	,	-122.26483	,	28));
        locations.add(newLocation(	47.491183	,	-122.26485	,	28));
        locations.add(newLocation(	47.488714	,	-122.26489	,	28));
        locations.add(newLocation(	47.491691	,	-122.26518	,	28));
        locations.add(newLocation(	47.488182	,	-122.26523	,	28));
        locations.add(newLocation(	47.461876	,	-122.26531	,	27));
        locations.add(newLocation(	47.492118	,	-122.26554	,	27));
        locations.add(newLocation(	47.487498	,	-122.2659	,	27));
        locations.add(newLocation(	47.493005	,	-122.26656	,	28));
        locations.add(newLocation(	47.448264	,	-122.26688	,	28));
        locations.add(newLocation(	47.465385	,	-122.26705	,	28));
        locations.add(newLocation(	47.46656	,	-122.26752	,	28));
        locations.add(newLocation(	47.44642	,	-122.2675	,	28));
        locations.add(newLocation(	47.4449	    ,	-122.26794	,	28));
        locations.add(newLocation(	47.46811	,	-122.26805	,	28));
        locations.add(newLocation(	47.472418	,	-122.26919	,	28));
        locations.add(newLocation(	47.484987	,	-122.26923	,	27));
        locations.add(newLocation(	47.473597	,	-122.26955	,	27));
        locations.add(newLocation(	47.474386	,	-122.26987	,	27));
        locations.add(newLocation(	47.435013	,	-122.2701	,	28));
        locations.add(newLocation(	47.496127	,	-122.2704	,	28));
        locations.add(newLocation(	47.433809	,	-122.27041	,	28));
        locations.add(newLocation(	47.4758 	,	-122.2706	,	28));
        locations.add(newLocation(	47.433015	,	-122.27077	,	28));
        locations.add(newLocation(	47.432332	,	-122.27119	,	28));
        locations.add(newLocation(	47.483026	,	-122.27158	,	28));
        locations.add(newLocation(	47.431502	,	-122.27187	,	28));
        locations.add(newLocation(	47.482598	,	-122.27195	,	28));
        locations.add(newLocation(	47.497794	,	-122.27228	,	28));
        locations.add(newLocation(	47.478899	,	-122.2724	,	28));
        locations.add(newLocation(	47.481888	,	-122.2724	,	28));
        locations.add(newLocation(	47.479627	,	-122.27263	,	28));
        locations.add(newLocation(	47.481235	,	-122.27265	,	28));
        locations.add(newLocation(	47.479559	,	-122.27267	,	28));
        locations.add(newLocation(	47.430708	,	-122.2727	,	28));
        locations.add(newLocation(	47.479972	,	-122.2727	,	28));
        locations.add(newLocation(	47.480612	,	-122.27275	,	28));
        locations.add(newLocation(	47.430026	,	-122.2736	,	28));
        locations.add(newLocation(	47.500957	,	-122.27562	,	28));
        locations.add(newLocation(	47.428718	,	-122.27581	,	28));
        locations.add(newLocation(	47.427932	,	-122.27704	,	28));
        locations.add(newLocation(	47.503011	,	-122.27771	,	28));
        locations.add(newLocation(	47.504073	,	-122.27872	,	28));
        locations.add(newLocation(	47.505577	,	-122.28	    ,	28));
        locations.add(newLocation(	47.422855	,	-122.28425	,	28));
        locations.add(newLocation(	47.421256	,	-122.28637	,	28));
        locations.add(newLocation(	47.420455	,	-122.2872	,	28));
        locations.add(newLocation(	47.515719	,	-122.28775	,	28));
        locations.add(newLocation(	47.419719	,	-122.28795	,	28));
        locations.add(newLocation(	47.516724	,	-122.2884	,	28));
        locations.add(newLocation(	47.517269	,	-122.28872	,	28));
        locations.add(newLocation(	47.418631	,	-122.28879	,	28));
        locations.add(newLocation(	47.417908	,	-122.28925	,	28));
        locations.add(newLocation(	47.417032	,	-122.28973	,	28));
        locations.add(newLocation(	47.416226	,	-122.29006	,	28));
        locations.add(newLocation(	47.385153	,	-122.29037	,	28));
        locations.add(newLocation(	47.386142	,	-122.29039	,	28));
        locations.add(newLocation(	47.38279	,	-122.29052	,	28));
        locations.add(newLocation(	47.387814	,	-122.29053	,	28));
        locations.add(newLocation(	47.521386	,	-122.29074	,	29));
        locations.add(newLocation(	47.381293	,	-122.29077	,	29));
        locations.add(newLocation(	47.391142	,	-122.29098	,	29));
        locations.add(newLocation(	47.379709	,	-122.2912	,	29));
        locations.add(newLocation(	47.522984	,	-122.29135	,	29));
        locations.add(newLocation(	47.377622	,	-122.29165	,	29));
        locations.add(newLocation(	47.524469	,	-122.2918	,	29));
        locations.add(newLocation(	47.408556	,	-122.29206	,	29));
        locations.add(newLocation(	47.525658	,	-122.29225	,	29));
        locations.add(newLocation(	47.407253	,	-122.29232	,	29));
        locations.add(newLocation(	47.401128	,	-122.29249	,	29));
        locations.add(newLocation(	47.405622	,	-122.29252	,	29));
        locations.add(newLocation(	47.402332	,	-122.29262	,	29));
        locations.add(newLocation(	47.403979	,	-122.29264	,	29));
        locations.add(newLocation(	47.526976	,	-122.29283	,	29));
        locations.add(newLocation(	47.343656	,	-122.293	,	29));
        locations.add(newLocation(	47.342623	,	-122.29305	,	29));
        locations.add(newLocation(	47.344586	,	-122.29306	,	29));
        locations.add(newLocation(	47.346167	,	-122.29332	,	29));
        locations.add(newLocation(	47.347446	,	-122.2937	,	29));
        locations.add(newLocation(	47.348948	,	-122.2944	,	29));
        locations.add(newLocation(	47.324893	,	-122.29456	,	29));
        locations.add(newLocation(	47.32266	,	-122.29495	,	29));
        locations.add(newLocation(	47.363394	,	-122.29535	,	29));
        locations.add(newLocation(	47.320948	,	-122.29544	,	29));
        locations.add(newLocation(	47.362275	,	-122.2956	,	29));
        locations.add(newLocation(	47.351872	,	-122.29587	,	29));
        locations.add(newLocation(	47.353226	,	-122.29634	,	29));
        locations.add(newLocation(	47.354129	,	-122.29657	,	29));
        locations.add(newLocation(	47.534634	,	-122.2966	,	29));
        locations.add(newLocation(	47.358536	,	-122.29662	,	29));
        locations.add(newLocation(	47.355161	,	-122.29674	,	29));
        locations.add(newLocation(	47.357567	,	-122.29679	,	29));
        locations.add(newLocation(	47.357005	,	-122.29682	,	29));
        locations.add(newLocation(	47.356211	,	-122.29683	,	29));
        locations.add(newLocation(	47.316768	,	-122.29712	,	29));
        locations.add(newLocation(	47.535545	,	-122.29726	,	29));
        locations.add(newLocation(	47.536492	,	-122.2982	,	29));
        locations.add(newLocation(	47.31378	,	-122.29826	,	29));
        locations.add(newLocation(	47.308317	,	-122.3005	,	29));
        locations.add(newLocation(	47.307129	,	-122.3009	,	29));
        locations.add(newLocation(	47.539452	,	-122.30186	,	29));
        locations.add(newLocation(	47.540768	,	-122.30338	,	29));
        locations.add(newLocation(	47.543298	,	-122.30593	,	29));
        locations.add(newLocation(	47.545006	,	-122.30756	,	29));
        locations.add(newLocation(	47.546281	,	-122.30886	,	29));
        locations.add(newLocation(	47.282466	,	-122.30929	,	29));
        locations.add(newLocation(	47.547021	,	-122.30979	,	29));
        locations.add(newLocation(	47.280236	,	-122.31037	,	29));
        locations.add(newLocation(	47.547719	,	-122.31081	,	29));
        locations.add(newLocation(	47.279032	,	-122.31105	,	29));
        locations.add(newLocation(	47.549298	,	-122.31357	,	29));
        locations.add(newLocation(	47.274326	,	-122.31397	,	29));
        locations.add(newLocation(	47.273415	,	-122.3146	,	29));
        locations.add(newLocation(	47.27161	,	-122.31597	,	29));
        locations.add(newLocation(	47.550742	,	-122.31599	,	29));
        locations.add(newLocation(	47.551195	,	-122.31657	,	29));
        locations.add(newLocation(	47.270272	,	-122.31714	,	29));
        locations.add(newLocation(	47.551907	,	-122.31724	,	29));
        locations.add(newLocation(	47.552601	,	-122.31772	,	29));
        locations.add(newLocation(	47.268516	,	-122.31891	,	29));
        locations.add(newLocation(	47.555484	,	-122.31947	,	29));
        locations.add(newLocation(	47.569614	,	-122.32037	,	29));
        locations.add(newLocation(	47.570173	,	-122.32042	,	29));
        locations.add(newLocation(	47.569762	,	-122.3204	,	29));
        locations.add(newLocation(	47.569137	,	-122.32045	,	29));
        locations.add(newLocation(	47.570437	,	-122.32049	,	28));
        locations.add(newLocation(	47.570614	,	-122.3206	,	28));
        locations.add(newLocation(	47.570786	,	-122.32078	,	28));
        locations.add(newLocation(	47.557344	,	-122.3209	,	28));
        locations.add(newLocation(	47.557733	,	-122.32115	,	28));
        locations.add(newLocation(	47.571001	,	-122.32118	,	29));
        locations.add(newLocation(	47.571102	,	-122.3215	,	30));
        locations.add(newLocation(	47.558998	,	-122.32176	,	30));
        locations.add(newLocation(	47.562138	,	-122.3219	,	30));
        locations.add(newLocation(	47.559938	,	-122.322	,	30));
        locations.add(newLocation(	47.560928	,	-122.322	,	28));
        locations.add(newLocation(	47.5711	    ,	-122.32459	,	28));
        locations.add(newLocation(	47.571201	,	-122.32518	,	28));
        locations.add(newLocation(	47.571401	,	-122.32597	,	28));
        locations.add(newLocation(	47.571401	,	-122.32597	,	28));
        locations.add(newLocation(	47.571401	,	-122.32597	,	28));
        locations.add(newLocation(	47.260364	,	-122.32778	,	28));
        locations.add(newLocation(	47.571426	,	-122.32897	,	28));
        locations.add(newLocation(	47.259078	,	-122.32898	,	28));
        locations.add(newLocation(	47.258033	,	-122.32982	,	28));
        locations.add(newLocation(	47.256959	,	-122.33058	,	28));
        locations.add(newLocation(	47.255922	,	-122.3312	,	27));
        locations.add(newLocation(	47.255	    ,	-122.33168	,	279));
        locations.add(newLocation(	47.571386	,	-122.33204	,	279));
        locations.add(newLocation(	47.253522	,	-122.33231	,	280));
        locations.add(newLocation(	47.252243	,	-122.33271	,	280));
        locations.add(newLocation(	47.571414	,	-122.33398	,	280));
        locations.add(newLocation(	47.575295	,	-122.33419	,	280));
        locations.add(newLocation(	47.580766	,	-122.33419	,	280));
        locations.add(newLocation(	47.580766	,	-122.33419	,	280));
        locations.add(newLocation(	47.580766	,	-122.33419	,	280));
        locations.add(newLocation(	47.574473	,	-122.3342	,	280));
        locations.add(newLocation(	47.574395	,	-122.3342	,	277));
        locations.add(newLocation(	47.571449	,	-122.3343	,	276));
        locations.add(newLocation(	47.571449	,	-122.3343	,	276));
        locations.add(newLocation(	47.571449	,	-122.3343	,	280));
        locations.add(newLocation(	47.573922	,	-122.33433	,	282));
        locations.add(newLocation(	47.245948	,	-122.3344	,	281));
        locations.add(newLocation(	47.245095	,	-122.33475	,	283));
        locations.add(newLocation(	47.580774	,	-122.33489	,	281));
        locations.add(newLocation(	47.580774	,	-122.33489	,	284));
        locations.add(newLocation(	47.244189	,	-122.33535	,	284));
        locations.add(newLocation(	47.243635	,	-122.33588	,	284));
        locations.add(newLocation(	47.581091	,	-122.33591	,	284));
        locations.add(newLocation(	47.243298	,	-122.33627	,	285));
        locations.add(newLocation(	47.242834	,	-122.33693	,	286));
        locations.add(newLocation(	47.24249	,	-122.33753	,	287));
        locations.add(newLocation(	47.242015	,	-122.33864	,	288));
        locations.add(newLocation(	47.241743	,	-122.33951	,	287));
        locations.add(newLocation(	47.241582	,	-122.3402	,	287));
        locations.add(newLocation(	47.241448	,	-122.34107	,	287));
        locations.add(newLocation(	47.241374	,	-122.34201	,	287));
        locations.add(newLocation(	47.240875	,	-122.35999	,	287));
        locations.add(newLocation(	47.240661	,	-122.37114	,	286));
        locations.add(newLocation(	47.240682	,	-122.37434	,	286));
        locations.add(newLocation(	47.240743	,	-122.37643	,	287));
        locations.add(newLocation(	47.240887	,	-122.37885	,	287));
        locations.add(newLocation(	47.242011	,	-122.3944	,	287));
        locations.add(newLocation(	47.242048	,	-122.39553	,	286));
        locations.add(newLocation(	47.241987	,	-122.39661	,	286));
        locations.add(newLocation(	47.241827	,	-122.39768	,	286));
        locations.add(newLocation(	47.241536	,	-122.39877	,	285));
        locations.add(newLocation(	47.240186	,	-122.40227	,	285));
        locations.add(newLocation(	47.23993	,	-122.40305	,	285));
        locations.add(newLocation(	47.23975	,	-122.40385	,	285));
        locations.add(newLocation(	47.239602	,	-122.40496	,	285));
        locations.add(newLocation(	47.239227	,	-122.41036	,	286));
        locations.add(newLocation(	47.239099	,	-122.41176	,	286));
        locations.add(newLocation(	47.238581	,	-122.41661	,	286));
        locations.add(newLocation(	47.238409	,	-122.41768	,	287));
        locations.add(newLocation(	47.238209	,	-122.4186	,	287));
        locations.add(newLocation(	47.237902	,	-122.41973	,	287));
        locations.add(newLocation(	47.237569	,	-122.42072	,	288));
        locations.add(newLocation(	47.234728	,	-122.42706	,	288));
        locations.add(newLocation(	47.234379	,	-122.42791	,	288));
        locations.add(newLocation(	47.234095	,	-122.42877	,	290));
        locations.add(newLocation(	47.23384	,	-122.4297	,	290));
        locations.add(newLocation(	47.233621	,	-122.43082	,	291));
        locations.add(newLocation(	47.233513	,	-122.43158	,	292));
        locations.add(newLocation(	47.140625	,	-122.43398	,	292));
        locations.add(newLocation(	47.157856	,	-122.43421	,	293));
        locations.add(newLocation(	47.157856	,	-122.43421	,	293));
        locations.add(newLocation(	47.157856	,	-122.43421	,	294));
        locations.add(newLocation(	47.140633	,	-122.43457	,	294));
        locations.add(newLocation(	47.23315	,	-122.43514	,	294));
        locations.add(newLocation(	47.157874	,	-122.43578	,	294));
        locations.add(newLocation(	47.232908	,	-122.43661	,	295));
        locations.add(newLocation(	47.158025	,	-122.43714	,	295));
        locations.add(newLocation(	47.232636	,	-122.4376	,	295));
        locations.add(newLocation(	47.158169	,	-122.44007	,	295));
        locations.add(newLocation(	47.158228	,	-122.44032	,	296));
        locations.add(newLocation(	47.158228	,	-122.44032	,	297));
        locations.add(newLocation(	47.158228	,	-122.44032	,	296));
        locations.add(newLocation(	47.231698	,	-122.44064	,	297));
        locations.add(newLocation(	47.230988	,	-122.4431	,	297));
        locations.add(newLocation(	47.230724	,	-122.44427	,	297));
        locations.add(newLocation(	47.230452	,	-122.44607	,	297));
        locations.add(newLocation(	47.23036	,	-122.4471	,	298));
        locations.add(newLocation(	47.230292	,	-122.44893	,	299));
        locations.add(newLocation(	47.230422	,	-122.45086	,	298));
        locations.add(newLocation(	47.231138	,	-122.45696	,	299));
        locations.add(newLocation(	47.158435	,	-122.45776	,	299));
        locations.add(newLocation(	47.231135	,	-122.45814	,	298));
        locations.add(newLocation(	47.231033	,	-122.45904	,	298));
        locations.add(newLocation(	47.158519	,	-122.45954	,	297));
        locations.add(newLocation(	47.230784	,	-122.46	    ,	296));
        locations.add(newLocation(	47.230357	,	-122.4611	,	296));
        locations.add(newLocation(	47.203716	,	-122.46141	,	296));
        locations.add(newLocation(	47.20429	,	-122.46145	,	297));
        locations.add(newLocation(	47.202926	,	-122.46145	,	297));
        locations.add(newLocation(	47.158717	,	-122.46147	,	297));
        locations.add(newLocation(	47.229921	,	-122.46182	,	297));
        locations.add(newLocation(	47.197441	,	-122.4623	,	296));
        locations.add(newLocation(	47.195764	,	-122.46242	,	295));
        locations.add(newLocation(	47.191188	,	-122.46251	,	294));
        locations.add(newLocation(	47.229314	,	-122.4625	,	294));
        locations.add(newLocation(	47.189985	,	-122.46265	,	293));
        locations.add(newLocation(	47.229025	,	-122.46278	,	294));
        locations.add(newLocation(	47.189042	,	-122.46285	,	293));
        locations.add(newLocation(	47.228583	,	-122.46307	,	293));
        locations.add(newLocation(	47.228177	,	-122.46326	,	292));
        locations.add(newLocation(	47.159029	,	-122.46339	,	291));
        locations.add(newLocation(	47.227729	,	-122.4634	,	291));
        locations.add(newLocation(	47.219758	,	-122.46343	,	291));
        locations.add(newLocation(	47.22685	,	-122.46347	,	291));
        locations.add(newLocation(	47.22148	,	-122.46353	,	291));
        locations.add(newLocation(	47.159288	,	-122.46459	,	291));
        locations.add(newLocation(	47.178291	,	-122.46583	,	290));
        locations.add(newLocation(	47.177348	,	-122.46616	,	290));
        locations.add(newLocation(	47.176712	,	-122.4665	,	290));
        locations.add(newLocation(	47.175925	,	-122.467	,	290));
        locations.add(newLocation(	47.175435	,	-122.46748	,	290));
        locations.add(newLocation(	47.167026	,	-122.47595	,	290));
        locations.add(newLocation(	47.167026	,	-122.47595	,	290));
        locations.add(newLocation(	47.167026	,	-122.47595	,	290));
        locations.add(newLocation(	47.166924	,	-122.47623	,	290));
        locations.add(newLocation(	47.165577	,	-122.4777	,	290));
        locations.add(newLocation(	47.165276	,	-122.47823	,	290));
        locations.add(newLocation(	47.164942	,	-122.47893	,	290));
        locations.add(newLocation(	47.164445	,	-122.48063	,	290));
        locations.add(newLocation(	47.16431	,	-122.48092	,	290));
        locations.add(newLocation(	47.1635	    ,	-122.48133	,	289));
        locations.add(newLocation(	47.1635	    ,	-122.48133	,	289));
        locations.add(newLocation(	47.1635	    ,	-122.48133	,	289));
        locations.add(newLocation(	47.164003	,	-122.48136	,	289));
        locations.add(newLocation(	47.163929	,	-122.4814	,	289));
        locations.add(newLocation(	47.163759	,	-122.4814	,	290));
    }
}
