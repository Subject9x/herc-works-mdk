package com.mech.works.data.load;

import java.util.ArrayList;
import java.util.List;
import com.mech.works.data.ref.HercRefId;
import com.mech.works.data.ref.WeaponRefId;

public final class GameDataRef {
    
    public static List<HercRefId> hercIdRef;
    public static List<WeaponRefId> wepIdRef;

    public GameDataRef(){
        setupHercId();
        setupWeaponId();
    }



    private static void setupHercId(){
        hercIdRef = new ArrayList<HercRefId>();

        hercIdRef.add(new HercRefId(new byte[]{0x00,0x00} , (byte)0x03, "outlaw")); //outlaw
        hercIdRef.add(new HercRefId(new byte[]{0x01,0x00} , (byte)0x05, "raptor")); //raptor
        hercIdRef.add(new HercRefId(new byte[]{0x02,0x00} , (byte)0x05, "tomahawk")); //tomahawk
        hercIdRef.add(new HercRefId(new byte[]{0x03,0x00} , (byte)0x07, "samson")); //samson
        hercIdRef.add(new HercRefId(new byte[]{0x04,0x00} , (byte)0x09, "colossus")); //colossus
        hercIdRef.add(new HercRefId(new byte[]{0x05,0x00} , (byte)0x09, "apocalypse")); //apocalypse
        hercIdRef.add(new HercRefId(new byte[]{0x06,0x00} , (byte)0x09, "ogre")); //ogre
        hercIdRef.add(new HercRefId(new byte[]{0x07,0x00} , (byte)0x04, "maverick")); //maverick
        hercIdRef.add(new HercRefId(new byte[]{0x08,0x00} , (byte)0x07, "razor")); //razor
    }

    private static void setupWeaponId(){
        wepIdRef = new ArrayList<WeaponRefId>();

        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x01, (byte)0x00}, new byte[]{(byte)0xc2, (byte)0x01}, "ATC20", "450"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x02, (byte)0x00}, new byte[]{(byte)0xa4, (byte)0x01}, "ATC35", "420"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x03, (byte)0x00}, new byte[]{(byte)0x7c, (byte)0x01}, "ATC50", "380"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x04, (byte)0x00}, new byte[]{(byte)0x4a, (byte)0x01}, "ATC75", "330"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x05, (byte)0x00}, new byte[]{(byte)0xc18, (byte)0x01}, "ATC100", "280"));

        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x06, (byte)0x00}, new byte[]{(byte)0x78, (byte)0x00}, "ELF", "120"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x07, (byte)0x00}, new byte[]{(byte)0x90, (byte)0x01}, "EMP", "400"));

        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x08, (byte)0x00}, new byte[]{(byte)0x40, (byte)0x01}, "LAS100", "320"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x09, (byte)0x00}, new byte[]{(byte)0x2c, (byte)0x01}, "LAS200", "300"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x0A, (byte)0x00}, new byte[]{(byte)0x18, (byte)0x01}, "LAS300", "280"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x0B, (byte)0x00}, new byte[]{(byte)0xfa, (byte)0x00}, "LAS400", "250"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x0C, (byte)0x00}, new byte[]{(byte)0xdc, (byte)0x00}, "LAS500", "220"));

        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x0D, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "MSL 6", "varies"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x0E, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "MSL 8", "varies"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x0F, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "MSL 10", "varies"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x10, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "MSL R", "varies"));

        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x11, (byte)0x00}, new byte[]{(byte)0xf0, (byte)0x00}, "PBW", "240"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x12, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "ECM", "none"));

        //empty
        //empty
        //empty

        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x16, (byte)0x00}, new byte[]{(byte)0x5a, (byte)0x00}, "ELF 2", "90"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x17, (byte)0x00}, new byte[]{(byte)0x2c, (byte)0x01}, "EMP 2", "300"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x18, (byte)0x00}, new byte[]{(byte)0xd2, (byte)0x00}, "PBW 2", "210"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x19, (byte)0x00}, new byte[]{(byte)0x78, (byte)0x00}, "PLAS", "120"));

        //empty
        //empty
        //empty

        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x1d, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "TARG", "none"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x1e, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "SHLD", "none"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x1f, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "TURB", "none"));
        wepIdRef.add(new WeaponRefId(new byte[]{(byte)0x20, (byte)0x00}, new byte[]{(byte)0x00, (byte)0x00}, "ENRG", "none"));


    }
}
