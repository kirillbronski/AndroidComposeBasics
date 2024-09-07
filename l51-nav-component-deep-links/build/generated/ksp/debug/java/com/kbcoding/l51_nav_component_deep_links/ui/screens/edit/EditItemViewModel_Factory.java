package com.kbcoding.l51_nav_component_deep_links.ui.screens.edit;

import com.kbcoding.l51_nav_component_deep_links.data.repository.ItemsRepository;
import dagger.internal.DaggerGenerated;
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
public final class EditItemViewModel_Factory {
  private final Provider<ItemsRepository> itemsRepositoryProvider;

  public EditItemViewModel_Factory(Provider<ItemsRepository> itemsRepositoryProvider) {
    this.itemsRepositoryProvider = itemsRepositoryProvider;
  }

  public EditItemViewModel get(int index) {
    return newInstance(index, itemsRepositoryProvider.get());
  }

  public static EditItemViewModel_Factory create(
      Provider<ItemsRepository> itemsRepositoryProvider) {
    return new EditItemViewModel_Factory(itemsRepositoryProvider);
  }

  public static EditItemViewModel newInstance(int index, ItemsRepository itemsRepository) {
    return new EditItemViewModel(index, itemsRepository);
  }
}
