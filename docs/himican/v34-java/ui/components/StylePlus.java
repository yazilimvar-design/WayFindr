package com.example.wayfindr.ui.components;

import com.example.wayfindr.dash.MapTheme;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class StylePlus {
    public static void enhance(JSONArray jSONArray, MapTheme mapTheme) {
        JSONObject optJSONObject;
        Object opt;
        Object opt2;
        try {
            JSONObject jSONObject = new JSONObject("{\"casingWidth\":[\"interpolate\",[\"exponential\",1.4],[\"zoom\"],6,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],1.6,\"primary\",1.1,[\"secondary\",\"tertiary\"],0,\"minor\",0,\"service\",0,[\"path\",\"track\"],0,\"rail\",0,0],10,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],3.4,\"primary\",2.9,[\"secondary\",\"tertiary\"],2.0,\"minor\",1.2,\"service\",0.6,[\"path\",\"track\"],0,\"rail\",0,0.5],13,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],6.2,\"primary\",5.4,[\"secondary\",\"tertiary\"],4.4,\"minor\",3.2,\"service\",2.0,[\"path\",\"track\"],0,\"rail\",0,1.0],16,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],14.5,\"primary\",13.2,[\"secondary\",\"tertiary\"],11.4,\"minor\",9.0,\"service\",5.4,[\"path\",\"track\"],0,\"rail\",0,2.5],19,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],40,\"primary\",35.5,[\"secondary\",\"tertiary\"],31,\"minor\",24.5,\"service\",14.5,[\"path\",\"track\"],0,\"rail\",0,5]],\"roadWidth\":[\"interpolate\",[\"exponential\",1.4],[\"zoom\"],6,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],0.8,\"primary\",0.5,[\"secondary\",\"tertiary\"],0,\"minor\",0,\"service\",0,[\"path\",\"track\"],0,\"rail\",0,0],10,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],2.2,\"primary\",1.8,[\"secondary\",\"tertiary\"],1.1,\"minor\",0.6,\"service\",0.3,[\"path\",\"track\"],0.3,\"rail\",0.4,0.3],13,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],4.2,\"primary\",3.6,[\"secondary\",\"tertiary\"],2.8,\"minor\",1.8,\"service\",1.0,[\"path\",\"track\"],0.6,\"rail\",0.7,0.6],16,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],11,\"primary\",10,[\"secondary\",\"tertiary\"],8.5,\"minor\",6.5,\"service\",3.5,[\"path\",\"track\"],1.4,\"rail\",1.2,1.4],19,[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],34,\"primary\",30,[\"secondary\",\"tertiary\"],26,\"minor\",20,\"service\",11,[\"path\",\"track\"],3,\"rail\",2.4,3]],\"HIMALAYAN\":{\"casing\":\"#5e554b\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#f5ad4a\",\"primary\",\"#ffe08a\",[\"secondary\",\"tertiary\"],\"#ffffff\",\"minor\",\"#ffffff\",\"service\",\"#fbf8f2\",[\"path\",\"track\"],\"#8a7d6e\",\"rail\",\"#9a958d\",\"#fbf8f2\"]},\"RALLY_DUST\":{\"casing\":\"#49382c\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#ef8f35\",\"primary\",\"#ffd06a\",[\"secondary\",\"tertiary\"],\"#fff8ea\",\"minor\",\"#fff8ea\",\"service\",\"#fff3dc\",[\"path\",\"track\"],\"#6e5a44\",\"rail\",\"#7d6f60\",\"#fff3dc\"]},\"JUNGLE_TRAIL\":{\"casing\":\"#070a07\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#f0c35a\",\"primary\",\"#d6e89a\",[\"secondary\",\"tertiary\"],\"#b9d28c\",\"minor\",\"#a7c27a\",\"service\",\"#8fa86a\",[\"path\",\"track\"],\"#6f8a55\",\"rail\",\"#5d6b50\",\"#8fa86a\"]},\"DESERT_RAID\":{\"casing\":\"#4d3422\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#ee8a3a\",\"primary\",\"#ffd36b\",[\"secondary\",\"tertiary\"],\"#ffffff\",\"minor\",\"#ffffff\",\"service\",\"#fff8e6\",[\"path\",\"track\"],\"#8a6a48\",\"rail\",\"#8f7d66\",\"#fff8e6\"]},\"NIGHT_EXPEDITION\":{\"casing\":\"#000000\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#ffc65a\",\"primary\",\"#f2ad45\",[\"secondary\",\"tertiary\"],\"#e39a35\",\"minor\",\"#d28a28\",\"service\",\"#a87024\",[\"path\",\"track\"],\"#6b5a40\",\"rail\",\"#4c4f55\",\"#a87024\"]},\"VOLCANIC\":{\"casing\":\"#0a0607\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#ff9d5c\",\"primary\",\"#f5814c\",[\"secondary\",\"tertiary\"],\"#ea6c3e\",\"minor\",\"#e05b32\",\"service\",\"#b04a2b\",[\"path\",\"track\"],\"#7a4a3a\",\"rail\",\"#5a4a48\",\"#b04a2b\"]},\"STEEL_STORM\":{\"casing\":\"#05070a\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#ffffff\",\"primary\",\"#e3eaf1\",[\"secondary\",\"tertiary\"],\"#c9d4df\",\"minor\",\"#b8c4d0\",\"service\",\"#94a1ae\",[\"path\",\"track\"],\"#5d6a78\",\"rail\",\"#4f5863\",\"#94a1ae\"]},\"CAMO_RAID\":{\"casing\":\"#0b0d08\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#f7dd85\",\"primary\",\"#ead07a\",[\"secondary\",\"tertiary\"],\"#ddbf68\",\"minor\",\"#d2b45f\",\"service\",\"#a8904c\",[\"path\",\"track\"],\"#7a7048\",\"rail\",\"#55533f\",\"#a8904c\"]},\"ICE_STORM\":{\"casing\":\"#4d5961\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#ffbf55\",\"primary\",\"#fff0b5\",[\"secondary\",\"tertiary\"],\"#ffffff\",\"minor\",\"#ffffff\",\"service\",\"#f4f9fb\",[\"path\",\"track\"],\"#7f8f97\",\"rail\",\"#8f9aa0\",\"#f4f9fb\"]},\"COPPER_RUSH\":{\"casing\":\"#0e0806\",\"road\":[\"match\",[\"get\",\"class\"],[\"motorway\",\"trunk\"],\"#ffc27a\",\"primary\",\"#efab63\",[\"secondary\",\"tertiary\"],\"#e29852\",\"minor\",\"#d58a45\",\"service\",\"#a86c38\",[\"path\",\"track\"],\"#7d5638\",\"rail\",\"#5a4a40\",\"#a86c38\"]}}");
            JSONArray jSONArray2 = jSONObject.getJSONArray("casingWidth");
            JSONArray jSONArray3 = jSONObject.getJSONArray("roadWidth");
            JSONObject optJSONObject2 = mapTheme != null ? jSONObject.optJSONObject(mapTheme.name()) : null;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject3 = jSONArray.optJSONObject(i);
                if (optJSONObject3 != null && (optJSONObject = optJSONObject3.optJSONObject("paint")) != null) {
                    String optString = optJSONObject3.optString("id");
                    if ("transportation-casing".equals(optString)) {
                        optJSONObject.put("line-width", jSONArray2);
                        if (optJSONObject2 != null && (opt2 = optJSONObject2.opt("casing")) != null) {
                            optJSONObject.put("line-color", opt2);
                        }
                    } else if ("transportation".equals(optString)) {
                        optJSONObject.put("line-width", jSONArray3);
                        if (optJSONObject2 != null && (opt = optJSONObject2.opt("road")) != null) {
                            optJSONObject.put("line-color", opt);
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }
}
