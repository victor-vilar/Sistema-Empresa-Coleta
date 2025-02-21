import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'currency'})
export class MockCurrencyPipe implements PipeTransform {
    transform(value: any): number {
        
        return value;
    }
}