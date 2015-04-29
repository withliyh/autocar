package com.cubic.autohome.common.skin;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class SkinObserable {
	  public ArrayList<ISkinUIObserver> list = new ArrayList();

	  public void registered(ISkinUIObserver paramISkinUIObserver)
	  {
	    this.list.add(paramISkinUIObserver);
	  }

	  public void unregistered(ISkinUIObserver paramISkinUIObserver)
	  {
	    Iterator localIterator = this.list.iterator();
	    while (true)
	    {
	      if (!localIterator.hasNext())
	        return;
	      if ((ISkinUIObserver)localIterator.next() == paramISkinUIObserver)
	        localIterator.remove();
	    }
	  }
}
