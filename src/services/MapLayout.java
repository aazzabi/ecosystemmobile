package services;


/**
 * 
 *  @author shannah
 */
public class MapLayout extends com.codename1.ui.layouts.Layout implements com.codename1.maps.MapListener {

	public MapLayout(MapContainer map, com.codename1.ui.Container actual) {
	}

	@java.lang.Override
	public void addLayoutComponent(Object value, com.codename1.ui.Component comp, com.codename1.ui.Container c) {
	}

	@java.lang.Override
	public void removeLayoutComponent(com.codename1.ui.Component comp) {
	}

	@java.lang.Override
	public boolean isConstraintTracking() {
            return false;
	}

	@java.lang.Override
	public Object getComponentConstraint(com.codename1.ui.Component comp) {
            return null;
	}

	@java.lang.Override
	public boolean isOverlapSupported() {
            return false;
	}

	public static void setHorizontalAlignment(com.codename1.ui.Component cmp, Float a) {
	}

	public static void setVerticalAlignment(com.codename1.ui.Component cmp, Float a) {
	}

	@java.lang.Override
	public void layoutContainer(com.codename1.ui.Container parent) {
	}

	@java.lang.Override
	public com.codename1.ui.geom.Dimension getPreferredSize(com.codename1.ui.Container parent) {
            return null;
	}

	@java.lang.Override
	public void mapPositionUpdated(com.codename1.ui.Component source, int zoom, com.codename1.maps.Coord center) {
	}
}
