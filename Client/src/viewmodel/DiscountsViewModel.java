package viewmodel;

import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;

public class DiscountsViewModel
{
  private LocalModel model;
  private ObservableList<String> discounts;
  private ListOfDiscountItems discountItems;

  public DiscountsViewModel(LocalModel model)
      throws SQLException, RemoteException
  {
    this.model = model;
    this.discounts = FXCollections.observableArrayList();
  }

  private void setDiscounts(String category) throws SQLException, RemoteException
  {
    discountItems = model.getDiscountItems();
    for (int i = 0; i < discountItems.getSize(); i++)
    {
      if (discountItems.getDiscountItem(i).getCategory().equals(category))
      {
        discounts.add(discountItems.getDiscountItem(i).getTitle() + ": " + discountItems.getDiscountItem(i).getDiscountPrice() + "DKK\n FROM " + discountItems.getDiscountItem(i).getNormalPrice() + "DKK");
      }
    }
  }

  public ObservableList<String> getDiscounts(String category)
      throws SQLException, RemoteException
  {
    setDiscounts(category);
    return discounts;
  }

  public void openLink(String oldValue)
  {
    String discountItem = oldValue.split(":")[0];
    String url = discountItems.getDiscountItemLinkByName(discountItem);
    if (url != null)
    {
      try {
        Desktop.getDesktop().browse(new URL(url).toURI());
      } catch (IOException | URISyntaxException e) {
        e.printStackTrace();
      }
    }
  }
}

