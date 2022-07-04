import { EventEmitter, Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { Ingredient } from "../shared/ingredient.model";
import { ShoppingService } from "../shopping-list/shopping-service";
import { Recipe } from "./recipe-model";

@Injectable()
export class RecipeService{
  //selectedRecipe = new Subject<Recipe>();  
  private recipes: Recipe[] = [
    new Recipe('A Test Recipe ', 'Test recipe', '../../assets/img/img1.jpg', [
      new Ingredient('meat', 4),
      new Ingredient('spagette', 1)
    ]),
    new Recipe('Anoter Test Recipe ', 'Test recipe', '../../assets/img/img2.jpg', [
      new Ingredient('Ice cream', 2),
      new Ingredient('milk', 1)
    ])
  ];

  constructor(private shopService : ShoppingService){}

  getRecipes(){
    return this.recipes.slice();
  }

  getRecipeById(id: number){
    return this.recipes[id];
  }

  AddIngredientsToShoppingList(ingredients: Ingredient[]){
    this.shopService.addIngredients(ingredients);
  }
}