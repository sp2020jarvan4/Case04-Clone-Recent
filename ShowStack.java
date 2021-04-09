@SuppressWarnings("deprecation")
public class DivStackPanel extends ComplexPanel {
	private static final String DEFAULT_STYLENAME = "gwt-StackPanel";

	private static final String DEFAULT_ITEM_STYLENAME = DEFAULT_STYLENAME
			+ "Item";

	private int visibleStack = -1;

	private Element body;

	Map<Integer, StackTextRow> rowTexts = new HashMap<Integer, DivStackPanel.StackTextRow>();

	/**
	 * Creates an empty stack panel.
	 */
	public DivStackPanel() {
		body = DOM.createDiv();
		setElement(body);
		DOM.sinkEvents(body, Event.ONCLICK);
		setStyleName(DEFAULT_STYLENAME);
	}

	public void showStack(int index) {
		if ((index >= getWidgetCount()) || (index < 0)
				|| (index == visibleStack)) {
			return;
		}
		if (visibleStack >= 0) {
			setStackVisible(visibleStack, false);
		}
		visibleStack = index;
		setStackVisible(visibleStack, true);
	}

	/**
	 * Adds a new child with the given widget.
	 * 
	 * @param w
	 *            the widget to be added
	 */
	@Override
	public void add(Widget w) {
		insert(w, getWidgetCount());
	}

	/**
	 * Adds a new child with the given widget and header.
	 * 
	 * @param w
	 *            the widget to be added
	 * @param stackText
	 *            the header text associated with this widget
	 */
	public void add(Widget w, String stackText) {
		add(w, stackText, false);
	}
}