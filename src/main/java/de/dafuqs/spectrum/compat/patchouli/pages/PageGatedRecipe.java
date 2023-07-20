package de.dafuqs.spectrum.compat.patchouli.pages;

import com.google.gson.annotations.*;
import de.dafuqs.spectrum.recipe.*;
import net.minecraft.client.*;
import net.minecraft.client.util.math.*;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.registry.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;
import vazkii.patchouli.api.*;
import vazkii.patchouli.client.book.*;
import vazkii.patchouli.client.book.gui.*;
import vazkii.patchouli.client.book.page.abstr.*;

/**
 * Like PageGatedRecipeDouble, but only displays a single recipe
 */
public abstract class PageGatedRecipe<T extends GatedRecipe> extends PageWithText implements GatedPatchouliPage {
	
	private final RecipeType<? extends T> recipeType;
	
	@SerializedName("recipe")
	Identifier recipeId;
	String title;
	
	protected transient T recipe;
	protected transient Text titleText;
	
	public PageGatedRecipe(RecipeType<? extends T> recipeType) {
		this.recipeType = recipeType;
	}
	
	private @Nullable T getRecipe(Identifier id) {
		if (MinecraftClient.getInstance().world == null) {
			return null;
		}
		RecipeManager manager = MinecraftClient.getInstance().world.getRecipeManager();
		return (T) manager.get(id).filter(recipe -> recipe.getType() == recipeType).orElse(null);
	}
	
	protected T loadRecipe(BookContentsBuilder builder, BookEntry entry, Identifier identifier) {
		if (identifier == null || MinecraftClient.getInstance().world == null) {
			return null;
		}
		T recipe = getRecipe(identifier);
		if (recipe != null) {
			entry.addRelevantStack(builder, recipe.getOutput(MinecraftClient.getInstance().world.getRegistryManager()), pageNum);
			return recipe;
		}
		PatchouliAPI.LOGGER.warn("Recipe {} (of type {}) not found", identifier, Registries.RECIPE_TYPE.getId(recipeType));
		return null;
	}
	
	@Override
	public boolean isPageUnlocked() {
		if (!super.isPageUnlocked() || recipe == null) {
			return false;
		}
		return recipe.canPlayerCraft(MinecraftClient.getInstance().player);
	}
	
	@Override
	public void build(World world, BookEntry entry, BookContentsBuilder builder, int pageNum) {
		super.build(world, entry, builder, pageNum);
		
		recipe = loadRecipe(builder, entry, recipeId);
		
		boolean customTitle = title != null && !title.isEmpty();
		titleText = !customTitle ? getRecipeOutput(world, recipe).getName() : i18nText(title);
		
		GatedPatchouliPage.runSanityCheck(entry.getId(), pageNum, advancement, recipe);
	}
	
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float tickDelta) {
		if (recipe != null) {
			int recipeX = getX();
			int recipeY = getY();
			drawRecipe(ms, recipe, recipeX, recipeY, mouseX, mouseY);
		}
		super.render(ms, mouseX, mouseY, tickDelta);
	}
	
	@Override
	public int getTextHeight() {
		return getY() + getRecipeHeight() - 13;
	}
	
	@Override
	public boolean shouldRenderText() {
		return getTextHeight() + 10 < GuiBook.PAGE_HEIGHT;
	}
	
	protected abstract void drawRecipe(MatrixStack ms, T recipe, int recipeX, int recipeY, int mouseX, int mouseY);
	
	protected abstract ItemStack getRecipeOutput(World world, T recipe);
	
	protected abstract int getRecipeHeight();
	
	protected int getX() {
		return GuiBook.PAGE_WIDTH / 2 - 49;
	}
	
	protected int getY() {
		return 4;
	}
	
	protected Text getTitle() {
		return titleText;
	}
	
}
