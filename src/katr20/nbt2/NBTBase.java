package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;

public abstract class NBTBase {
    public static byte STRING_TAG = 0;
    public static byte INTEGER_TAG = 1;
    public static byte BOOLEAN_TAG = 2;
    public static byte STRING_LIST_TAG = 3;
    private String key;

    public NBTBase(String key) {
	this.key = key;
    }

    public static void writeTag(NBTBase base, DataOutput output) throws IOException {
	output.writeByte(base.getTagType());
	base.writeTagContents(output);
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	byte b;
	try {
	    b = input.readByte();
	} catch (EOFException e) {
	    return null;
	}

	if (b == NBTBase.STRING_TAG) {
	    return NBTString.readTag(input);
	}
	if (b == NBTBase.INTEGER_TAG) {
	    return NBTInteger.readTag(input);
	}
	if (b == NBTBase.BOOLEAN_TAG) {
	    return NBTBoolean.readTag(input);
	}
	if (b == NBTBase.STRING_LIST_TAG) {
	    return NBTStringList.readTag(input);
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
    public abstract byte getTagType();
    public abstract NBTBase cloneTag();

    public abstract Object getValue();
}
