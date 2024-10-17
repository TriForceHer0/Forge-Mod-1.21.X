
package com.triforcehero.mod.datagen;

import com.triforcehero.mod.Mod;
import com.triforcehero.mod.block.ModBlocks;
import com.triforcehero.mod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> LEAD_SMELTABLES = List.of(ModItems.RAWLEAD.get(),
                ModBlocks.LEAD_ORE.get(), ModBlocks.LEAD_DEEPSLATE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEAD_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.LEAD.get())
                .unlockedBy(getHasName(ModItems.LEAD.get()), has(ModItems.LEAD.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD.get(), 9)
                .requires(ModBlocks.LEAD_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LEAD_BLOCK.get()), has(ModBlocks.LEAD_BLOCK.get())).save(pRecipeOutput);

        oreSmelting(pRecipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD.get(), 0.25f, 200, "Lead");
        oreBlasting(pRecipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD.get(), 0.25f, 100, "Lead");

        List<ItemLike> MAGNESIUM_SMELTABLES = List.of(ModItems.MAGNESIUM.get(),
                ModBlocks.MAGNESIUM_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAGCHUNK_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.MAGNESIUM.get())
                .unlockedBy(getHasName(ModItems.MAGNESIUM.get()), has(ModItems.MAGNESIUM.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAGNESIUM.get(), 9)
                .requires(ModBlocks.MAGCHUNK_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.MAGCHUNK_BLOCK.get()), has(ModBlocks.MAGCHUNK_BLOCK.get())).save(pRecipeOutput);

        oreSmelting(pRecipeOutput, MAGNESIUM_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD.get(), 0.5f, 200, "Magnesium");
        oreBlasting(pRecipeOutput, MAGNESIUM_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD.get(), 0.5f, 100, "Magnesium");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Mod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
