package com.example.wayfindr.dash.map;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public final class LocPlus {
    public static volatile long fusedAt;
    public static volatile boolean fusedGood;
    public static volatile float gpsAcc;
    public static volatile long gpsAt;
    public static volatile boolean hiAcc;
    public static volatile float stBear;
    public static volatile int stFast;
    public static volatile boolean stHasB;
    public static volatile double stLat;
    public static volatile double stLng;
    public static volatile boolean stOn;
    public static volatile int stOut;
    public static volatile int stSlow;
    public static volatile double stW;

    final class Exec implements Executor {
        private final Handler h;

        public Exec(Handler handler) {
            this.h = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.h.post(runnable);
        }
    }

    public static void addFused(LocationManager locationManager, LocationListener locationListener, Looper looper) {
        try {
            if (Build.VERSION.SDK_INT < 31 || !locationManager.hasProvider("fused")) {
                return;
            }
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            LocationRequest.Builder builder = new LocationRequest.Builder(500L);
            builder.setQuality(100);
            locationManager.requestLocationUpdates("fused", builder.build(), new Exec(new Handler(looper)), locationListener);
            hiAcc = true;
            log("fused high accuracy on");
        } catch (Throwable th) {
            hiAcc = false;
            log("fused high accuracy failed: ".concat(th.toString()));
            try {
                if (locationManager.isProviderEnabled("fused")) {
                    locationManager.requestLocationUpdates("fused", 500L, 0.0f, locationListener, looper);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void log(String str) {
        try {
            RideDiagnostics.INSTANCE.log("gps", str);
        } catch (Throwable unused) {
        }
    }

    public static boolean preferReject(Location location, Location location2, double d) {
        if (location2 == null) {
            return false;
        }
        try {
            String provider = location2.getProvider();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            float accuracy = location2.hasAccuracy() ? location2.getAccuracy() : 99.0f;
            if ("fused".equals(provider)) {
                if (hiAcc) {
                    fusedAt = elapsedRealtime;
                    if (elapsedRealtime - gpsAt <= 2000) {
                        float f = gpsAcc * 2.0f;
                        if (f < 15.0f) {
                            f = 15.0f;
                        }
                        if (accuracy > f) {
                            fusedGood = false;
                            return true;
                        }
                    }
                    fusedGood = true;
                    return false;
                }
            } else if ("gps".equals(provider)) {
                gpsAt = elapsedRealtime;
                gpsAcc = accuracy;
                if (!hiAcc || !fusedGood) {
                    return false;
                }
                long j = fusedAt;
                return j > 0 && elapsedRealtime - j <= 3000;
            }
            return location != null && rank(location.getProvider()) > 1 && d <= 10.0d;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int rank(String str) {
        return "fused".equals(str) ? hiAcc ? 3 : 1 : "gps".equals(str) ? 2 : 1;
    }

    public static double stDist(double d, double d2, double d3, double d4) {
        double d5 = (d3 - d) * 111195.07973436d;
        double cos = (d4 - d2) * 111195.07973436d * Math.cos((d + d3) * 0.5d * 0.017453292519943295d);
        return Math.sqrt((d5 * d5) + (cos * cos));
    }

    public static void stMerge(double d, double d2, double d3) {
        double d4 = stW + d3;
        double d5 = d3 / d4;
        double d6 = stLat;
        stLat = d6 + ((d - d6) * d5);
        double d7 = stLng;
        stLng = d7 + ((d2 - d7) * d5);
        stW = d4;
    }

    public static void stillLock(Location location) {
        if (location != null) {
            try {
                if (location.hasSpeed()) {
                    float speed = location.getSpeed();
                    float accuracy = location.hasAccuracy() ? location.getAccuracy() : 30.0f;
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    if (speed >= 2.0f && location.hasBearing()) {
                        stBear = location.getBearing();
                        stHasB = true;
                    }
                    float max = Math.max(accuracy, 3.0f);
                    double d = 9.0f / (max * max);
                    double d2 = accuracy;
                    if (!stOn) {
                        if (speed >= 1.0f || accuracy > 60.0f) {
                            stSlow = 0;
                            return;
                        }
                        if (stSlow != 0) {
                            double stDist = stDist(stLat, stLng, latitude, longitude);
                            double d3 = 3.0d * d2;
                            if (d3 < 30.0d) {
                                d3 = 30.0d;
                            }
                            if (stDist <= d3) {
                                stMerge(latitude, longitude, d);
                                int i = stSlow + 1;
                                stSlow = i;
                                if (i < 3) {
                                    return;
                                }
                                stOn = true;
                                stOut = 0;
                                stFast = 0;
                            }
                        }
                        stLat = latitude;
                        stLng = longitude;
                        stW = d;
                        stSlow = 1;
                        return;
                    }
                    if (speed < 4.0f) {
                        if (speed >= 2.0f) {
                            int i2 = stFast + 1;
                            stFast = i2;
                            if (i2 < 2) {
                            }
                        } else {
                            stFast = 0;
                        }
                        double stDist2 = stDist(stLat, stLng, latitude, longitude);
                        double d4 = 2.5d * d2;
                        if (d4 < 12.0d) {
                            d4 = 12.0d;
                        }
                        if (stDist2 <= d4) {
                            stOut = 0;
                            int i3 = stSlow;
                            if (i3 < 8) {
                                stSlow = i3 + 1;
                                stMerge(latitude, longitude, d);
                            }
                        } else {
                            int i4 = stOut + 1;
                            stOut = i4;
                            if (i4 < 6) {
                            }
                        }
                    }
                    stOn = false;
                    stSlow = 0;
                    stFast = 0;
                    stOut = 0;
                    return;
                    location.setLatitude(stLat);
                    location.setLongitude(stLng);
                    location.setSpeed(0.0f);
                    if (stHasB) {
                        location.setBearing(stBear);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }
}
