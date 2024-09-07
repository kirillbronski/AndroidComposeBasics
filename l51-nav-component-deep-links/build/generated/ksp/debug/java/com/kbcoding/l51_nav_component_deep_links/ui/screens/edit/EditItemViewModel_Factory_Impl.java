package com.kbcoding.l51_nav_component_deep_links.ui.screens.edit;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class EditItemViewModel_Factory_Impl implements EditItemViewModel.Factory {
  private final EditItemViewModel_Factory delegateFactory;

  EditItemViewModel_Factory_Impl(EditItemViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public EditItemViewModel create(int index) {
    return delegateFactory.get(index);
  }

  public static Provider<EditItemViewModel.Factory> create(
      EditItemViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new EditItemViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<EditItemViewModel.Factory> createFactoryProvider(
      EditItemViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new EditItemViewModel_Factory_Impl(delegateFactory));
  }
}
