package com.kbcoding.l51_nav_component_deep_links.ui.screens.items;

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
public final class ItemsViewModel_Factory implements Factory<ItemsViewModel> {
  private final Provider<ItemsRepository> repositoryProvider;

  public ItemsViewModel_Factory(Provider<ItemsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ItemsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ItemsViewModel_Factory create(Provider<ItemsRepository> repositoryProvider) {
    return new ItemsViewModel_Factory(repositoryProvider);
  }

  public static ItemsViewModel newInstance(ItemsRepository repository) {
    return new ItemsViewModel(repository);
  }
}
