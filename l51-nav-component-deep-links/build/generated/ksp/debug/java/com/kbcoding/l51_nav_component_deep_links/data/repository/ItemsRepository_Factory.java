package com.kbcoding.l51_nav_component_deep_links.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ItemsRepository_Factory implements Factory<ItemsRepository> {
  @Override
  public ItemsRepository get() {
    return newInstance();
  }

  public static ItemsRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ItemsRepository newInstance() {
    return new ItemsRepository();
  }

  private static final class InstanceHolder {
    private static final ItemsRepository_Factory INSTANCE = new ItemsRepository_Factory();
  }
}
