package org.hercworks.core.data.ref.constants;

import at.favre.lib.bytes.Bytes;

public final class DTSBoneId {  
    public static Bytes DTS_BONE_ID_ORIGIN = Bytes.allocate(2, (byte)0x0000);
    public static Bytes DTS_BONE_ID_CALF_LEFT = Bytes.allocate(2, (byte)0x0100);
    public static Bytes DTS_BONE_ID_CALF_RIGHT = Bytes.allocate(2, (byte)0x0200);
    public static Bytes DTS_BONE_ID_THIGH_LEFT = Bytes.allocate(2, (byte)0x0300);
    public static Bytes DTS_BONE_ID_THIGH_RIGHT = Bytes.allocate(2, (byte)0x0400);
    public static Bytes DTS_BONE_ID_CENTER_TORSO = Bytes.allocate(2, (byte)0x0500);
    public static Bytes DTS_BONE_ID_FOOT_RIGHT = Bytes.allocate(2, (byte)0x0600);
    public static Bytes DTS_BONE_ID_FOOT_LEFT = Bytes.allocate(2, (byte)0x0700);
    public static Bytes DTS_BONE_ID_HEAD = Bytes.allocate(2, (byte)0x0800);
    public static Bytes DTS_BONE_ID_CAMERA = Bytes.allocate(2, (byte)0x0900);
    public static Bytes DTS_BONE_ID_CENTER1 = Bytes.allocate(2, (byte)0x0a00);
    public static Bytes DTS_BONE_ID_CENTER2 = Bytes.allocate(2, (byte)0x0b00);
    public static Bytes DTS_BONE_ID_PELVIS = Bytes.allocate(2, (byte)0x0c00);
    public static Bytes DTS_BONE_ID_UNK = Bytes.allocate(2, (byte)0x0d00);
    public static Bytes DTS_BONE_ID_ANKLE_RIGHT = Bytes.allocate(2, (byte)0x0e00);
    public static Bytes DTS_BONE_ID_ANKLE_LEFT = Bytes.allocate(2, (byte)0x0f00);
    public static Bytes DTS_BONE_ID_TOE_LEFT = Bytes.allocate(2, (byte)0x1000);
    public static Bytes DTS_BONE_ID_TOE_RIGHT = Bytes.allocate(2, (byte)0x1100);
}