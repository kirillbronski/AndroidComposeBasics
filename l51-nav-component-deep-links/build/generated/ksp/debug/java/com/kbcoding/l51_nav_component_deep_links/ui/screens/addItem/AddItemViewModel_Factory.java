package com.kbcoding.l51_nav_component_deep_links.ui.screens.addItem;

import com.kbcoding.l51_nav_component_deep_links.data.repository.ItemsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class AddItemViewModel_Factory implements Factory<AddItemViewModel> {
  private final Provider<ItemsRepository> repositoryProvider;

  public AddItemViewModel_Factory(Provider<ItemsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddItemViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddItemViewModel_Factory create(Provider<ItemsRepository> repositoryProvider) {
    return new AddItemViewModel_Factory(repositoryProvider);
  }

  public static AddItemViewModel newInstance(ItemsRepository repository) {
    return new AddItemViewModel(repository);
  }
}
