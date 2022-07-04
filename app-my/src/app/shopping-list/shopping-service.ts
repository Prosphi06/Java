import { EventEmitter } from "@angular/core";
import { Ingredient } from "../shared/ingredient.model";
import { Subject } from "rxjs";

export class ShoppingService{
   starEditing = new Subject<number>();
   ingredientsChanged = new Subject<Ingredient[]>();
   private ingredients: Ingredient[] =  [
        new Ingredient('Eggs', 12),
        new Ingredient('Bread', 1),
        new Ingredient('Tomatoes', 7)
    ]

    getIngredients(){
        return this.ingredients.slice();
    }

    getIngredient(index: number){
        return this.ingredients[index];
    }

    addIngrediant(ingredient: Ingredient){
        this.ingredients.push(ingredient);
        this.ingredientsChanged.next(this.ingredients.slice());
    }

    addIngredients(ingredients: Ingredient[]){
        // for(let ingredient of ingredients){
        //     this.addIngrediant(ingredient);
        this.ingredients.push(...ingredients);
        this.ingredientsChanged.next(this.ingredients.slice())
       
    }

    updateIngredient(index: number, newIngredient: Ingredient){
        this.ingredients[index] = newIngredient;
        this.ingredientsChanged.next(this.ingredients.slice());
    }

    deleteIngredient(index: number) {
        this.ingredients.splice(index, 1);
        this.ingredientsChanged.next(this.ingredients.slice());
       
    }
}