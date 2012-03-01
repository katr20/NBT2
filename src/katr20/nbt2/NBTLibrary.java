package katr20.nbt2;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class NBTLibrary {
    private HashMap<String, NBTBase> tagMap = new HashMap<String, NBTBase>();
    private File file;

    public NBTLibrary(String path, boolean read) throws IOException {
	this(new File(path), read);
    }
    public NBTLibrary(File file, boolean read) throws IOException {
	this.file = file;

	if (read) {
	    readTags();
	}
    }

    public void readTags() throws IOException {
	FileInputStream reader = null;
	DataInput input = null;
	try {
	    if ((this.file!=null) && this.file.exists()) {
		input = new DataInputStream((reader = new FileInputStream(this.file)));
		NBTBase base;
		while ((base = NBTBase.readTag(input)) != null) {
		    this.tagMap.put(base.getKey(), base);
		}
	    }
	} finally {
	    if (reader!=null) {
		reader.close();
	    }
	}
    }

    public void writeTags() throws IOException {
	FileOutputStream writer = null;
	DataOutput output = null;
	try {
	    output = new DataOutputStream(new FileOutputStream(this.file));
	    for (NBTBase tag : this.tagMap.values()) {
		NBTBase.writeTag(tag, output);
	    }
	} finally {
	    if (writer!=null) {
		writer.close();
	    }
	}
    }

    public NBTBase getTag(String key) {
	return this.tagMap.get(key);
    }

    public void putTag(NBTBase tag) {
	this.tagMap.put(tag.getKey(), tag);
    }

    public NBTBase[] getTags() {
	return this.tagMap.values().toArray(new NBTBase[this.tagMap.size()]);
    }
}
