package pattern.compent;

public class TextFile extends AbstractFile {
	private String name;

	@Override
	public void add(AbstractFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(AbstractFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractFile getChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void killVirus() {
		System.out.println("****对文本文件'" + name + "'进行杀毒");
	}

	/**
	 * @param name
	 */
	public TextFile(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
