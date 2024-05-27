import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { Observable, map, startWith } from 'rxjs';
import { getWeekdayValues } from '../enums/Weekday';

@Component({
  selector: 'app-days-chip',
  templateUrl: './days-chip.component.html',
  styleUrls: ['./days-chip.component.css']
})
export class DaysChipComponent implements OnInit {

  daysCtrl = new FormControl('');
  filteredDays: Observable<string[]>;
  @Input()
  days: string[];
  allDays: any[] = getWeekdayValues();

  @ViewChild('dayInput') dayInput: ElementRef<HTMLInputElement>;

  constructor() {
    this.filteredDays = this.daysCtrl.valueChanges.pipe(
      startWith(null),
      map((day: string | null) => (day ? this._filter(day) : this.allDays.slice())),
    );
   }

  ngOnInit(): void {
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.days.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();

    this.daysCtrl.setValue(null);
  }

  remove(day: string): void {
    const index = this.days.indexOf(day);

    if (index >= 0) {
      this.days.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.days.push(event.option.viewValue);
    this.dayInput.nativeElement.value = '';
    this.daysCtrl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.allDays.filter(day => day.toLowerCase().includes(filterValue));
  }

}
