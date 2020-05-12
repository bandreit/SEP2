package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private ServerViewModel serverViewModel;

  public ViewModelFactory(Model model) throws Exception
  {
    serverViewModel = new ServerViewModel(model);
  }

  public ServerViewModel getServerViewModel()
  {
    return serverViewModel;
  }
}
