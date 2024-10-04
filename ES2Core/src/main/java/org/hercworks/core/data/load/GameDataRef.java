package org.hercworks.core.data.load;

import java.util.ArrayList;
import java.util.List;

import org.hercworks.core.data.ref.constants.HercDataRef;
import org.hercworks.core.data.ref.constants.ItemDataRef;

import at.favre.lib.bytes.Bytes;

public final class GameDataRef {
    
    public List<HercDataRef> hercIdRef;
    public List<ItemDataRef> wepIdRef;
    public List<byte[]> dtsBoneId;

    public GameDataRef(){
        setupHercId();
        setupWeaponId();
    }

    private void setupHercId(){
        hercIdRef = new ArrayList<HercDataRef>();

        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0000) , Bytes.allocate(1, (byte)0x03), "outlaw")); //outlaw
        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0100) , Bytes.allocate(1, (byte)0x05), "raptor")); //raptor        
        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0200) , Bytes.allocate(1, (byte)0x05), "tomahawk")); //tomahawk
        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0300) , Bytes.allocate(1, (byte)0x07), "samson")); //samson
        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0400) , Bytes.allocate(1, (byte)0x09), "colossus")); //colossus
        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0500) , Bytes.allocate(1, (byte)0x09), "apocalypse")); //apocalypse
        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0600) , Bytes.allocate(1, (byte)0x09), "ogre")); //ogre
        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0700) , Bytes.allocate(1, (byte)0x04), "maverick")); //maverick
        hercIdRef.add(new HercDataRef(Bytes.allocate(2, (byte)0x0800) , Bytes.allocate(1, (byte)0x07), "razor")); //razor
    }

    private void setupWeaponId(){
        wepIdRef = new ArrayList<ItemDataRef>();
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0100), Bytes.allocate(2, (byte)0xc201), "ATC20", "450"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0200), Bytes.allocate(2, (byte)0xa401), "ATC35", "420"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0300), Bytes.allocate(2, (byte)0x7c01), "ATC50", "380"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0400), Bytes.allocate(2, (byte)0x4a01), "ATC75", "330"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0500), Bytes.allocate(2, (byte)0x1801), "ATC100", "280"));
        
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0600), Bytes.allocate(2, (byte)0x7800), "ELF", "120"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0700), Bytes.allocate(2, (byte)0x9001), "EMP", "400"));
        
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0800), Bytes.allocate(2, (byte)0x4001), "LAS100", "320"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0900), Bytes.allocate(2, (byte)0x2c01), "LAS200", "300"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0A00), Bytes.allocate(2, (byte)0x1801), "LAS300", "280"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0B00), Bytes.allocate(2, (byte)0xfa00), "LAS400", "250"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0C00), Bytes.allocate(2, (byte)0xdc00), "LAS500", "220"));
        
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0D00), Bytes.allocate(2, (byte)0x0000), "MSL 6", "varies"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0E00), Bytes.allocate(2, (byte)0x0000), "MSL 8", "varies"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x0F00), Bytes.allocate(2, (byte)0x0000), "MSL 10", "varies"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1000), Bytes.allocate(2, (byte)0x0000), "MSL R", "varies"));
        
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1100), Bytes.allocate(2, (byte)0xf000), "PBW", "240"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1200), Bytes.allocate(2, (byte)0x0000), "ECM", "none"));
               
        //empty
        //empty
        //empty
               
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1600), Bytes.allocate(2, (byte)0x5a00), "ELF 2", "90"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1700), Bytes.allocate(2, (byte)0x2c01), "EMP 2", "300"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1800), Bytes.allocate(2, (byte)0xd200), "PBW 2", "210"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1900), Bytes.allocate(2, (byte)0x7800), "PLAS", "120"));
               
        //empty
        //empty
        //empty
               
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1d00), Bytes.allocate(2, (byte)0x0000), "TARG", "none"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1e00), Bytes.allocate(2, (byte)0x0000), "SHLD", "none"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x1f00), Bytes.allocate(2, (byte)0x0000), "TURB", "none"));
        wepIdRef.add(new ItemDataRef(Bytes.allocate(2, (byte)0x2000), Bytes.allocate(2, (byte)0x0000), "ENRG", "none"));
    }
}
