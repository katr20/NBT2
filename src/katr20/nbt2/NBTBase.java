package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;

public abstract class NBTBase {

    private String key;

    public NBTBase(String key) {
	this.key = key;
    }

    public static void writeTag(NBTBase base, DataOutput output)
	    throws IOException {
	output.writeByte(base.getTagType().getTagIdentifier());
	base.writeTagContents(output);
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	try {
	    byte b = input.readByte();

	    NBTTagType tagType = NBTTagType.fromByte(b);

	    if (tagType == NBTTagType.STRING_TAG) {
		return NBTString.readTag(input);
	    }
	    if (tagType == NBTTagType.INTEGER_TAG) {
		return NBTInteger.readTag(input);
	    }
	    if (tagType == NBTTagType.BOOLEAN_TAG) {
		return NBTBoolean.readTag(input);
	    }
	    if (tagType == NBTTagType.STRING_LIST_TAG) {
		return NBTStringList.readTag(input);
	    }
	    if (tagType == NBTTagType.LONG_TAG) {
		return NBTLong.readTag(input);
	    }
	    if (tagType == NBTTagType.DOUBLE_TAG) {
		return NBTDouble.readTag(input);
	    }
	    if (tagType == NBTTagType.FLOAT_TAG) {
		return NBTFloat.readTag(input);
	    }
	} catch (EOFException e) {
	    return null;
	}
	return null;
    }

    public String getKey() {
	return this.key;
    }

    public void setKey(String str) {
	this.key = str;
    }

    public abstract void writeTagContents(DataOutput output) throws IOException;

    public abstract NBTTagType getTagType();

    public abstract NBTBase cloneTag();

    public abstract Object getValue();
}
