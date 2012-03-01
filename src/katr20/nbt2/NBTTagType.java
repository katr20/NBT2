package katr20.nbt2;

public enum NBTTagType {
    STRING_TAG (0), INTEGER_TAG    (1),
    BOOLEAN_TAG(2), STRING_LIST_TAG(3),
    LONG_TAG   (4), DOUBLE_TAG     (5),
    FLOAT_TAG  (6);

    private byte tagIdent;

    private NBTTagType(int identity) {
	this.tagIdent = (byte) identity;
    }

    public byte getTagIdentifier() {
	return this.tagIdent;
    }

    public static NBTTagType fromByte(byte b) {
	for (NBTTagType type : NBTTagType.values()) {
	    if (type.getTagIdentifier() == b) {
		return type;
	    }
	}
	return null;
    }

    public static NBTTagType fromTag(NBTBase base) {
	return base.getTagType();
    }

    public static byte identifierFromTag(NBTBase base) {
	return base.getTagType().getTagIdentifier();
    }
}
