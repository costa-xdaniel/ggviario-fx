package st.ggviario.house.model;

import st.ggviario.house.control.HomeController;

public interface DrawerItem {

    void setMenu( Menu menu );

    void setRootController(HomeController rootController);
}
