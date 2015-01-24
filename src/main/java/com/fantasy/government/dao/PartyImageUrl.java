package com.fantasy.government.dao;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class PartyImageUrl {
    
    private static final Map<String, String> PARTY_NAME_TO_IMAGE_URL;
    
    public static String getPartyImageUrl(String partyName) {
        return PARTY_NAME_TO_IMAGE_URL.get(partyName);
    }
    
    static {
        ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();
        builder.put("יש עתיד", "http://upload.wikimedia.org/wikipedia/he/thumb/0/0a/YeshAtidLogo.svg/190px-YeshAtidLogo.svg.png");
        builder.put("הליכוד", "http://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Likud_Logo.svg/250px-Likud_Logo.svg.png");
        builder.put("העבודה", "http://upload.wikimedia.org/wikipedia/he/thumb/a/a6/Logo_of_the_Israeli_Labor_Party.svg/190px-Logo_of_the_Israeli_Labor_Party.svg.png");
        builder.put("ישראל ביתנו", "http://upload.wikimedia.org/wikipedia/he/thumb/0/01/Israelblogo.JPG/250px-Israelblogo.JPG");
        builder.put("הבית היהודי", "http://upload.wikimedia.org/wikipedia/he/b/bc/%D7%94%D7%A1%D7%9E%D7%9C_%D7%A9%D7%9C_%D7%9E%D7%A4%D7%9C%D7%92%D7%AA_%22%D7%94%D7%91%D7%99%D7%AA_%D7%94%D7%99%D7%94%D7%95%D7%93%D7%99_-_%D7%9E%D7%A4%D7%93%D7%9C_%D7%94%D7%97%D7%93%D7%A9%D7%94%22_%D7%91%D7%A8%D7%90%D7%A9%D7%95%D7%AA_%D7%A0%D7%A4%D7%AA%D7%9C%D7%99_%D7%91%D7%A0%D7%98.png");
        builder.put("ש”ס", "http://upload.wikimedia.org/wikipedia/he/thumb/8/89/Shas.png/150px-Shas.png");
        builder.put("יהדות התורה", "http://upload.wikimedia.org/wikipedia/he/a/a6/Yahadutlogo.JPG");
        builder.put("התנועה", "http://upload.wikimedia.org/wikipedia/he/thumb/3/32/HatnuaTzipiLogo.svg/200px-HatnuaTzipiLogo.svg.png");
        builder.put("מרצ", "http://upload.wikimedia.org/wikipedia/he/thumb/8/8b/Meretz_2012_Logo.jpg/200px-Meretz_2012_Logo.jpg");
        builder.put(" רע”מ-תע”ל", "http://upload.wikimedia.org/wikipedia/he/thumb/e/e2/Raam-taal-mada.JPG/250px-Raam-taal-mada.JPG");
        builder.put("חזית דמוקרטית לשלום ושוויון", "http://upload.wikimedia.org/wikipedia/he/thumb/9/90/Hadash_2013.png/200px-Hadash_2013.png");
        builder.put("ברית לאומית דמוקרטית", "http://upload.wikimedia.org/wikipedia/he/d/d9/Baladlogo.jpg");
        builder.put("קדימה", "http://upload.wikimedia.org/wikipedia/he/thumb/e/ec/Logo_of_the_Kadima_Party.svg/250px-Logo_of_the_Kadima_Party.svg.png");
        PARTY_NAME_TO_IMAGE_URL = builder.build();
    }
    
}
