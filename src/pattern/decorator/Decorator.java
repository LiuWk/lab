package pattern.decorator;

public class Decorator extends Component {
	private Component component;//维持一个对抽象构件对象的引用
	
	public Decorator(Component component) {//注入一个抽象构件类型的对象
		super();
		this.component = component;
	}

	@Override
	void operation() {
		component.operation();//调用原有业务方法
	}

}
