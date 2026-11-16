package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.data.ApiUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Pair;

/* loaded from: classes2.dex */
public final class SearchPlus {
    public static int addAddresses(List list, String str, ArrayList arrayList) {
        int i = 0;
        if (list != null) {
            try {
                for (Object obj : list) {
                    if (obj instanceof Address) {
                        Address address = (Address) obj;
                        if (address.hasLatitude() && address.hasLongitude() && !isDup(arrayList, address.getLatitude(), address.getLongitude())) {
                            String addressLine = address.getMaxAddressLineIndex() >= 0 ? address.getAddressLine(0) : null;
                            String nameFor = nameFor(address, addressLine, str);
                            if (addressLine == null || addressLine.equals(nameFor)) {
                                addressLine = "";
                            }
                            arrayList.add(new PlaceSearch.Hit(nameFor, address.getLatitude(), address.getLongitude(), "Google", addressLine));
                            i++;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return i;
    }

    public static int addGeocoder(Context context, String str, Pair pair, ArrayList arrayList, int i) {
        try {
            if (!Geocoder.isPresent()) {
                return 0;
            }
            ApiUsage.INSTANCE.record(ApiUsage.GEOCODE);
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            if (pair != null) {
                try {
                    double doubleValue = ((Number) pair.getFirst()).doubleValue();
                    double doubleValue2 = ((Number) pair.getSecond()).doubleValue();
                    double d = doubleValue + 0.5d;
                    int addAddresses = addAddresses(geocoder.getFromLocationName(str, i, doubleValue - 0.5d, doubleValue2 - 0.7d, d, doubleValue2 + 0.7d), str, arrayList);
                    if (addAddresses > 0) {
                        return addAddresses;
                    }
                } catch (Exception unused) {
                }
            }
            return addAddresses(geocoder.getFromLocationName(str, i), str, arrayList);
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static List augment(Context context, String str, Pair pair, List list) {
        try {
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                arrayList.addAll(list);
            }
            NavPlus.plSearch(context, str, arrayList);
            if (str != null) {
                String trim = str.trim();
                if (trim.length() >= 2 && addGeocoder(context, trim, pair, arrayList, 10) > 0) {
                    sortByScore(trim, arrayList);
                }
            }
            return arrayList;
        } catch (Throwable unused) {
            return list;
        }
    }

    public static Object fallback(Object obj, Context context, String str, Pair pair) {
        if (obj == null && str != null) {
            try {
                String trim = str.trim();
                if (trim.length() >= 2) {
                    ArrayList arrayList = new ArrayList();
                    if (NavPlus.plSearch(context, str, arrayList) > 0 || addGeocoder(context, trim, pair, arrayList, 5) > 0) {
                        sortByScore(trim, arrayList);
                        return arrayList.get(0);
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return obj;
    }

    public static boolean isDup(ArrayList arrayList, double d, double d2) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof PlaceSearch.Hit) {
                PlaceSearch.Hit hit = (PlaceSearch.Hit) obj;
                if (Math.abs(hit.getLat() - d) < 3.0E-4d && Math.abs(hit.getLng() - d2) < 4.0E-4d) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String nameFor(Address address, String str, String str2) {
        String featureName = address.getFeatureName();
        if (featureName != null) {
            String trim = featureName.trim();
            if (trim.length() != 0 && !trim.matches("[0-9][0-9A-Za-z/ -]{0,7}")) {
                return trim;
            }
        }
        if (str == null) {
            return str2;
        }
        int indexOf = str.indexOf(44);
        if (indexOf > 0) {
            String trim2 = str.substring(0, indexOf).trim();
            if (trim2.length() != 0) {
                return trim2;
            }
        }
        return str;
    }

    public static void sortByScore(String str, ArrayList arrayList) {
        int size = arrayList.size();
        if (size >= 2) {
            int[] iArr = new int[size];
            int i = 0;
            PlaceSearch placeSearch = PlaceSearch.INSTANCE;
            for (int i2 = 0; i2 < size; i2++) {
                Object obj = arrayList.get(i2);
                int i3 = 0;
                if (obj instanceof PlaceSearch.Hit) {
                    PlaceSearch.Hit hit = (PlaceSearch.Hit) obj;
                    String name = hit.getName();
                    String subtitle = hit.getSubtitle();
                    if (subtitle != null) {
                        name = name.concat(" ").concat(subtitle);
                    }
                    i3 = placeSearch.wordScore(str, name);
                }
                iArr[i2] = i3;
                if (i3 > i) {
                    i = i3;
                }
            }
            ArrayList arrayList2 = new ArrayList(size);
            for (int i4 = i; i4 >= 0; i4--) {
                for (int i5 = 0; i5 < size; i5++) {
                    if (iArr[i5] == i4) {
                        arrayList2.add(arrayList.get(i5));
                    }
                }
            }
            arrayList.clear();
            arrayList.addAll(arrayList2);
        }
    }
}
