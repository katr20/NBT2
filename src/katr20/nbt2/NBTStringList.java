package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTStringList extends NBTBase {
    private String[] tagValue;

    public NBTStringList(String key) {
	super(key);
    }

    public NBTStringList(String key, String[] value) {
	super(key);
	this.tagValue = value;
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	String key = input.readUTF();
	byte isNull = input.readByte();
	if (isNull == 0) {
	    int listSize = input.readInt();
	    String[] values = new String[listSize];
	    for (int i = 0; i < listSize; i++) {
		values[i] = input.readUTF();
	    }
	    return new NBTStringList(key, values);
	}
	return new NBTStringList(key, null);
    }

    public void setValue(String[] str) {
	this.tagValue = str;
    }

    @Override
    public String[] getValue() {
	return this.tagValue;
    }

    @Override
    public void writeTagContents(DataOutput output) throws IOException {
	output.writeUTF(getKey());
	if (this.tagValue != null) {
	    output.writeByte(0);
	    output.writeInt(this.tagValue.length);
	    for (String str : this.tagValue) {
		output.writeUTF(str);
	    }
	} else {
	    output.writeByte(1);
	}
    }

    @Override
    public NBTTagType getTagType() {
	return NBTTagType.STRING_LIST_TAG;
    }

    @Override
    public NBTBase cloneTag() {
	return new NBTStringList(getKey(), this.tagValue);
    }

}
